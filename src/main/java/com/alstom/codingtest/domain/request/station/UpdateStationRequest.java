package com.alstom.codingtest.domain.request.station;

import com.alstom.codingtest.entity.StationContactInformation;

import java.util.List;

public record UpdateStationRequest(
        String name,
        double latitude,
        double longitude
) {
}
