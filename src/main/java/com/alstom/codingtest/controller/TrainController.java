package com.alstom.codingtest.controller;

import com.alstom.codingtest.domain.request.train.CreateTrainRequest;
import com.alstom.codingtest.domain.request.train.UpdateTrainRequest;
import com.alstom.codingtest.domain.response.train.TrainResponse;
import com.alstom.codingtest.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/trains")
    public List<TrainResponse> getTrains() {
        return trainService.getTrains();
    }

    @GetMapping("/train/{trainId}")
    public TrainResponse getTrainById(@PathVariable(value = "trainId") String trainId) {
        return trainService.getTrainResponseById(trainId);
    }

    @PostMapping("/train")
    public TrainResponse createTrain(@RequestBody CreateTrainRequest request) {
        return trainService.createTrain(request);
    }

    @PutMapping("/train/{trainId}")
    public TrainResponse updateTrain(@PathVariable(value = "trainId") String trainId, @RequestBody UpdateTrainRequest request) {
        return trainService.updateTrain(trainId, request);
    }

    @DeleteMapping("/train/{trainId}")
    public void deleteTrain(@PathVariable(value = "trainId") String trainId) {
        trainService.deleteTrain(trainId);
    }
}
