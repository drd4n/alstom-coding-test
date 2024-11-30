package com.alstom.codingtest.controller;

import com.alstom.codingtest.domain.request.ticket.CreateTicketRequest;
import com.alstom.codingtest.domain.request.ticket.UpdateTicketRequest;
import com.alstom.codingtest.domain.response.ticket.TicketResponse;
import com.alstom.codingtest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public List<TicketResponse> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/tickets/{ticketId}")
    public TicketResponse getTicket(@PathVariable(value = "ticketId") long ticketId) {
        return ticketService.getTicketResponseById(ticketId);
    }

    @PostMapping("/ticket")
    public TicketResponse createTicket(@RequestBody CreateTicketRequest createTicketRequest) {
        return ticketService.createTicket(createTicketRequest);
    }

    @PutMapping("/ticket/{ticketId}")
    public TicketResponse updateTicket(@PathVariable(value = "ticketId") long ticketId, @RequestBody UpdateTicketRequest updateTicketRequest) {
        return ticketService.updateTicket(ticketId, updateTicketRequest);
    }

    @DeleteMapping("/ticket/{ticketId}")
    public void deleteTicket(@PathVariable(value = "ticketId") long ticketId) {
        ticketService.deleteTicket(ticketId);
    }
}
