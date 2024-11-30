package com.alstom.codingtest.service;

import com.alstom.codingtest.domain.request.train.CreateTrainRequest;
import com.alstom.codingtest.domain.request.train.UpdateTrainRequest;
import com.alstom.codingtest.domain.response.train.TrainResponse;
import com.alstom.codingtest.entity.Station;
import com.alstom.codingtest.entity.Train;
import com.alstom.codingtest.exception.TrainErrorMessage;
import com.alstom.codingtest.mapper.TrainMapper;
import com.alstom.codingtest.repository.TrainRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainMapper trainMapper;

    public List<TrainResponse> getTrains() {
        return trainRepository.findAll()
                .stream()
                .map(trainMapper::toTrainResponse)
                .toList();
    }

    public Train getTrainById(String trainId) {
        return trainRepository.findById(trainId)
                .orElseThrow(() -> {
                    log.error("Train not found with id: {}", trainId);
                    throw new EntityNotFoundException(TrainErrorMessage.TRAIN_NOT_FOUND.getMessage(trainId));
                });
    }

    public TrainResponse getTrainResponseById(String trainId) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> {
                    log.error("Train not found with id: {}", trainId);
                    throw new EntityNotFoundException(TrainErrorMessage.TRAIN_NOT_FOUND.getMessage(trainId));
                });
        return trainMapper.toTrainResponse(train);
    }

    private String generateTrainId(Station originStation, Station destinationStation) {
        String maxId = trainRepository.countByOriginAndDestination(originStation.getId(), destinationStation.getId());
        Long maxIdLong = maxId == null ? null : Long.parseLong(maxId.split("-")[1]);
        if (maxId == null) {
            return String.format("%s%s-%03d", originStation.getName().substring(0, 3), destinationStation.getName().substring(0, 3), 1);
        }
        return String.format("%s%s-%03d", originStation.getName().substring(0, 3), destinationStation.getName().substring(0, 3), maxIdLong + 1);
    }

    @Transactional
    public TrainResponse createTrain(CreateTrainRequest request) {
        Station originStation = stationService.getStationById(request.originStationId());
        Station destinationStation = stationService.getStationById(request.destinationStationId());
        String trainId = generateTrainId(originStation, destinationStation);
        Train train = trainRepository.save(trainMapper.fromCreateTrainRequest(trainId ,request, originStation, destinationStation));
        log.info("Created train with id: {}", train.getId());
        return trainMapper.toTrainResponse(train);
    }

    @Transactional
    public TrainResponse updateTrain(String trainId, UpdateTrainRequest request) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> {
                    log.error("Train with id: {} not found", trainId);
                    throw new EntityNotFoundException(TrainErrorMessage.TRAIN_NOT_FOUND.getMessage(trainId));
                });
        Station originStation = stationService.getStationById(request.originStationId());
        Station destinationStation = stationService.getStationById(request.destinationStationId());
        trainMapper.patch(train, request, originStation, destinationStation);
        log.info("Updated train with id: {}", train.getId());
        return trainMapper.toTrainResponse(trainRepository.save(train));
    }

    @Transactional
    public void deleteTrain(String trainId) {
        log.info("Deleting train with id: {}", trainId);
        trainRepository.deleteById(trainId);
    }


}
