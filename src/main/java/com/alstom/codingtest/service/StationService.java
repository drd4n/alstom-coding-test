package com.alstom.codingtest.service;

import com.alstom.codingtest.domain.request.station.CreateStationRequest;
import com.alstom.codingtest.domain.request.station.UpdateStationRequest;
import com.alstom.codingtest.domain.response.station.StationResponse;
import com.alstom.codingtest.entity.Station;
import com.alstom.codingtest.entity.StationContactInformation;
import com.alstom.codingtest.exception.StationErrorMessage;
import com.alstom.codingtest.mapper.StationMapper;
import com.alstom.codingtest.repository.StationContactInformationRepository;
import com.alstom.codingtest.repository.StationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private StationContactInformationRepository stationContactInformationRepository;

    public List<StationResponse> getStations() {
        return stationRepository.findAll()
                .stream()
                .map(stationMapper::toStationResponse)
                .toList();
    }

    public StationResponse getStationResponseById(long stationId) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> {
                    log.error("Station not found with id: {}", stationId);
                    throw new EntityNotFoundException(StationErrorMessage.STATION_NOT_FOUND.getMessage(String.valueOf(stationId)));
                });
        return stationMapper.toStationResponse(station);
    }

    public Station getStationById(long stationId) {
        return stationRepository.findById(stationId)
                .orElseThrow(() -> {
                    log.error("Station not found with id: {}", stationId);
                    throw new EntityNotFoundException(StationErrorMessage.STATION_NOT_FOUND.getMessage(String.valueOf(stationId)));
                });
    }

    @Transactional
    public StationResponse createStation(CreateStationRequest createStationRequest) {
        Station station = stationRepository.save(stationMapper.fromCreateStationRequest(createStationRequest));
        log.info("Created station id: {}", station.getId());
        if (createStationRequest.contactInformations() != null && createStationRequest.contactInformations().size() > 0) {
            List<StationContactInformation> stationContactInformations = createStationRequest.contactInformations()
                    .stream()
                    .map(information -> stationMapper.fromCreateStationContactInformationRequest(information, station))
                    .toList();
            stationContactInformationRepository.saveAll(stationContactInformations);
            log.info("Create contact information total of: {} entities", stationContactInformations.size());
        }
        return stationMapper.toStationResponse(station);
    }

    @Transactional
    public StationResponse updateStation(long stationId, UpdateStationRequest updateStationRequest) {
        Station station = getStationById(stationId);
        stationMapper.patch(station, updateStationRequest);
        log.info("Updated station id: {}", station.getId());
        return stationMapper.toStationResponse(stationRepository.save(station));
    }

    @Transactional
    public void deleteStationById(long stationId) {
        log.info("Delete station id: {}", stationId);
        stationRepository.deleteById(stationId);
    }
}
