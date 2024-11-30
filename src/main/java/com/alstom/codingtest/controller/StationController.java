package com.alstom.codingtest.controller;

import com.alstom.codingtest.domain.request.station.CreateStationRequest;
import com.alstom.codingtest.domain.request.station.UpdateStationRequest;
import com.alstom.codingtest.domain.request.train.CreateTrainRequest;
import com.alstom.codingtest.domain.request.train.UpdateTrainRequest;
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

}
