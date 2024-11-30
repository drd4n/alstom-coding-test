package com.alstom.codingtest.domain.request.train;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record CreateTrainRequest(
        @NotBlank(message = "Departure time cannot be blank")
        LocalTime departureTime,

        @NotBlank(message = "Arrival time cannot be blank")
        LocalTime arrivalTime,
        long originStationId,
        long destinationStationId,
        @Size(min = 1, message = "Seat capacity must be grater than 0")
        int seatCapacity
) {
}
