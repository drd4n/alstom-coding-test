package com.alstom.codingtest.domain.response.train;

import java.time.LocalTime;

public record TrainResponse(
    String id,
    LocalTime departureTime,
    LocalTime arrivalTime,
    long originStationId,
    long destinationStationId,
    int seatCapacity
) {
}
