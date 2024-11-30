package com.alstom.codingtest.domain.request.ticket;

import java.time.LocalDate;

public record UpdateTicketRequest(
        String passengerName,
        String seatNumber,
        LocalDate departureDate,
        String trainId
) {
}
