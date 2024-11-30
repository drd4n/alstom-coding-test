package com.alstom.codingtest.mapper;

import com.alstom.codingtest.domain.request.train.CreateTrainRequest;
import com.alstom.codingtest.domain.request.train.UpdateTrainRequest;
import com.alstom.codingtest.domain.response.train.TrainResponse;
import com.alstom.codingtest.entity.Station;
import com.alstom.codingtest.entity.Train;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrainMapper {

    @Mapping(target = "id", source = "id")
    Train fromCreateTrainRequest(String id, CreateTrainRequest createTrainRequest, Station originStation, Station destinationStation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "train.originStation", source = "originStation")
    @Mapping(target = "train.destinationStation", source = "destinationStation")
    void patch(@MappingTarget Train train, UpdateTrainRequest createTrainRequest, Station originStation, Station destinationStation);

    @Mapping(target = "originStationId", source = "train.originStation.id")
    @Mapping(target = "destinationStationId", source = "train.destinationStation.id")
    TrainResponse toTrainResponse(Train train);
}
