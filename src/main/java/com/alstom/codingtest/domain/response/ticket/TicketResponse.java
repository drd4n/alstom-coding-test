package com.alstom.codingtest.domain.response.ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public record TicketResponse(
        long id,
        String passengerName,
        String seatNumber,
        LocalDate departureDate,
        LocalTime departureTime,
        LocalTime arrivalTime,
        String trainId,
        String originStationName,
        String destinationStationName
) {
}
