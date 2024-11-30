package com.alstom.codingtest.domain.request.station;

public record CreateStationContactInformationRequest(
    String type,
    String information
) {
}
