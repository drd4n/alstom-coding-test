package com.alstom.codingtest.domain.response.station;

import com.alstom.codingtest.entity.enums.StationContactInformationType;

public record StationContactInformationResponse(
        StationContactInformationType type,
        String information
) {
}