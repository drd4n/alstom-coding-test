package com.alstom.codingtest.mapper;

import com.alstom.codingtest.domain.request.station.CreateStationContactInformationRequest;
import com.alstom.codingtest.domain.request.station.CreateStationRequest;
import com.alstom.codingtest.domain.request.station.UpdateStationContactInformationRequest;
import com.alstom.codingtest.domain.request.station.UpdateStationRequest;
import com.alstom.codingtest.domain.response.station.StationContactInformationResponse;
import com.alstom.codingtest.domain.response.station.StationResponse;
import com.alstom.codingtest.entity.Station;
import com.alstom.codingtest.entity.StationContactInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {

    StationResponse toStationResponse(Station station);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactInformations", ignore = true)
    Station fromCreateStationRequest(CreateStationRequest createStationRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactInformations", ignore = true)
    void patch(@MappingTarget Station station, UpdateStationRequest updateStationRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "station", ignore = true)
    void patch(@MappingTarget StationContactInformation stationContactInformation, UpdateStationContactInformationRequest updateStationContactInformationRequest);

    @Mapping(target = "id", ignore = true)
    StationContactInformation fromCreateStationContactInformationRequest(CreateStationContactInformationRequest createStationContactInformationRequest, Station station);

    StationContactInformationResponse toStationContactInformationResponse(StationContactInformation stationContactInformation);
}
