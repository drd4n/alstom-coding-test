package com.alstom.codingtest.domain.response.station;

import com.alstom.codingtest.entity.StationContactInformation;

import java.util.List;

public record StationResponse(
    long id,
    String name,
    double latitude,
    double longitude,
    List<StationContactInformationResponse> contactInformations
) {
}
