package com.alstom.codingtest.domain.request.train;

import java.time.LocalTime;

public record UpdateTrainRequest(
    LocalTime departureTime,
    LocalTime arrivalTime,
    long originStationId,
    long destinationStationId,
    int seatCapacity
) {
}
