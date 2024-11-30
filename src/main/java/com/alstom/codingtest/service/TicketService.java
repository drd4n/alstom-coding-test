package com.alstom.codingtest.service;

import com.alstom.codingtest.domain.request.ticket.CreateTicketRequest;
import com.alstom.codingtest.domain.request.ticket.UpdateTicketRequest;
import com.alstom.codingtest.domain.response.ticket.TicketResponse;
import com.alstom.codingtest.entity.Ticket;
import com.alstom.codingtest.entity.Train;
import com.alstom.codingtest.exception.TicketErrorMessage;
import com.alstom.codingtest.mapper.TicketMapper;
import com.alstom.codingtest.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TrainService trainService;

    public List<TicketResponse> getTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::toTicketResponse)
                .toList();
    }

    public TicketResponse getTicketResponseById(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> {
                    log.error("Ticket not found: {}", ticketId);
                    throw new EntityNotFoundException(TicketErrorMessage.TICKET_NOT_FOUND.getMessage(String.valueOf(ticketId)));
                });
        return ticketMapper.toTicketResponse(ticket);
    }

    @Transactional
    public TicketResponse createTicket(CreateTicketRequest createTicketRequest) {
        if (createTicketRequest.departureDate().isBefore(LocalDate.now())) {
            log.error("Invalid departure date: {}", createTicketRequest.departureDate());
            throw new RuntimeException(TicketErrorMessage.INVALID_DEPARTURE_DATE.getMessage());
        }
        Train train = trainService.getTrainById(createTicketRequest.trainId());
        List<Ticket> bookedTickets = ticketRepository.findAllByTrainAndDepartureDate(train, createTicketRequest.departureDate());
        int seatCount = bookedTickets.size();
        if (seatCount >= train.getSeatCapacity()) {
            log.error("Train is fully booked: {}", train.getId());
            throw new RuntimeException(TicketErrorMessage.TRAIN_FULLY_BOOKED.getMessage(train.getId()));
        }
        Set<String> bookedSeatNumbers = bookedTickets.stream().map(Ticket::getSeatNumber).collect(Collectors.toSet());
        if (bookedSeatNumbers.contains(createTicketRequest.seatNumber())){
            log.error("Seat is already booked: {}", createTicketRequest.seatNumber());
            throw new RuntimeException(TicketErrorMessage.SEAT_NOT_AVAILABLE.getMessage(createTicketRequest.seatNumber()));
        }
        Ticket ticket = ticketMapper.fromCreateTicketRequest(createTicketRequest, train);
        ticket = ticketRepository.save(ticket);
        log.info("Created ticket with id: {}", ticket.getId());
        return ticketMapper.toTicketResponse(ticket);
    }

    private Ticket getTicketById(long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException(TicketErrorMessage.TICKET_NOT_FOUND.getMessage(String.valueOf(ticketId))));
    }

    @Transactional
    public TicketResponse updateTicket(long ticketId, UpdateTicketRequest updateTicketRequest) {
        if (updateTicketRequest.departureDate().isBefore(LocalDate.now())) {
            log.error("Invalid departure date: {}", updateTicketRequest.departureDate());
            throw new RuntimeException(TicketErrorMessage.INVALID_DEPARTURE_DATE.getMessage());
        }
        Train train = trainService.getTrainById(updateTicketRequest.trainId());
        List<Ticket> bookedTickets = ticketRepository.findAllByTrainAndDepartureDate(train, updateTicketRequest.departureDate());
        Set<String> bookedSeatNumbers = bookedTickets.stream().map(Ticket::getSeatNumber).collect(Collectors.toSet());
        if (bookedSeatNumbers.contains(updateTicketRequest.seatNumber())){
            log.error("Seat is already booked: {}", updateTicketRequest.seatNumber());
            throw new RuntimeException(TicketErrorMessage.SEAT_NOT_AVAILABLE.getMessage(updateTicketRequest.seatNumber()));
        }
        Ticket ticket = getTicketById(ticketId);
        ticketMapper.patch(ticket, updateTicketRequest,train);
        ticket = ticketRepository.save(ticket);
        log.info("Updated ticket with id: {}", ticket.getId());
        return ticketMapper.toTicketResponse(ticket);
    }

    @Transactional
    public void deleteTicket(long ticketId) {
        log.info("Deleting ticket with id: {}", ticketId);
        ticketRepository.deleteById(ticketId);
    }
}
