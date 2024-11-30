package com.alstom.codingtest.domain.request.station;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateStationRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,
        double latitude,
        double longitude,
        List<CreateStationContactInformationRequest> contactInformations
) {
}
