package com.alstom.codingtest.domain.request.station;

public record UpdateStationContactInformationRequest(
    String type,
    String information
) {
}
