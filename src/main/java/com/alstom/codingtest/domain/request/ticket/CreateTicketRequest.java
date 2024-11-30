package com.alstom.codingtest.domain.request.ticket;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateTicketRequest(
        @NotBlank(message = "Passenger name cannot be blank")
        String passengerName,

        @NotBlank(message = "Seat number cannot be blank")
        String seatNumber,

        @NotBlank(message = "Departure date cannot be blank")
        LocalDate departureDate,

        @NotBlank(message = "Train id cannot be blank")
        String trainId
) {
}
