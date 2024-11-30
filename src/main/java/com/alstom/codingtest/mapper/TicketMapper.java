package com.alstom.codingtest.mapper;

import com.alstom.codingtest.domain.request.ticket.CreateTicketRequest;
import com.alstom.codingtest.domain.request.ticket.UpdateTicketRequest;
import com.alstom.codingtest.domain.request.train.CreateTrainRequest;
import com.alstom.codingtest.domain.request.train.UpdateTrainRequest;
import com.alstom.codingtest.domain.response.ticket.TicketResponse;
import com.alstom.codingtest.domain.response.train.TrainResponse;
import com.alstom.codingtest.entity.Station;
import com.alstom.codingtest.entity.Ticket;
import com.alstom.codingtest.entity.Train;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "departureTime", source = "ticket.train.departureTime")
    @Mapping(target = "arrivalTime", source = "ticket.train.arrivalTime")
    @Mapping(target = "trainId", source = "ticket.train.id")
    @Mapping(target = "originStationName", source = "ticket.train.originStation.name")
    @Mapping(target = "destinationStationName", source = "ticket.train.destinationStation.name")
    TicketResponse toTicketResponse(Ticket ticket);

    @Mapping(target = "id", ignore = true)
    Ticket fromCreateTicketRequest(CreateTicketRequest createTicketRequest, Train train);

    @Mapping(target = "id", ignore = true)
    void patch(@MappingTarget Ticket ticket, UpdateTicketRequest updateTicketRequest, Train train);
}
