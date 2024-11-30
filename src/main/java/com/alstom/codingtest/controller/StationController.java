package com.alstom.codingtest.controller;

import com.alstom.codingtest.domain.request.station.CreateStationContactInformationRequest;
import com.alstom.codingtest.domain.request.station.CreateStationRequest;
import com.alstom.codingtest.domain.request.station.UpdateStationContactInformationRequest;
import com.alstom.codingtest.domain.request.station.UpdateStationRequest;
import com.alstom.codingtest.domain.request.train.CreateTrainRequest;
import com.alstom.codingtest.domain.request.train.UpdateTrainRequest;
import com.alstom.codingtest.domain.response.station.StationContactInformationResponse;
import com.alstom.codingtest.domain.response.station.StationResponse;
import com.alstom.codingtest.domain.response.train.TrainResponse;
import com.alstom.codingtest.service.StationService;
import com.alstom.codingtest.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/stations")
    public List<StationResponse> getTrains() {
        return stationService.getStations();
    }

    @GetMapping("/station/{stationId}")
    public StationResponse getTrainById(@PathVariable(value = "stationId") String stationId) {
        return stationService.getStationResponseById(Long.parseLong(stationId));
    }

    @PostMapping("/station")
    public StationResponse createStation(@RequestBody CreateStationRequest createStationRequest) {
        return stationService.createStation(createStationRequest);
    }

    @PutMapping("/station/{stationId}")
    public StationResponse updateStation(@PathVariable(value = "stationId") String stationId, @RequestBody UpdateStationRequest updateStationRequest) {
        return stationService.updateStation(Long.parseLong(stationId), updateStationRequest);
    }

    @DeleteMapping("/station/{stationId}")
    public void deleteStation(@PathVariable(value = "stationId") String stationId) {
        stationService.deleteStationById(Long.parseLong(stationId));
    }

    @PostMapping("/station/{stationId}/contact")
    public StationContactInformationResponse createStationContactInformation(@PathVariable(value = "stationId") String stationId, @RequestBody CreateStationContactInformationRequest request) {
        return stationService.addStationContactInformation(Long.parseLong(stationId), request);
    }

    @PutMapping("/station/{stationId}/contact/{stationContactInformationId}")
    public StationContactInformationResponse updateStationContactInformation(@PathVariable(value = "stationId") String stationId, @PathVariable(value = "stationContactInformationId") String stationContactInformationId, @RequestBody UpdateStationContactInformationRequest updateStationContactInformationRequest) {
        return stationService.updateStationContactInformation(Long.parseLong(stationContactInformationId), updateStationContactInformationRequest);
    }

    @DeleteMapping("/station/{stationId}/contact/{stationContactInformationId}")
    public void deleteStationContactInformation(@PathVariable(value = "stationId") String stationId, @PathVariable(value = "stationContactInformationId") String stationContactInformationId) {
        stationService.deleteStationContactInformationById(Long.parseLong(stationContactInformationId));
    }

}
