package com.example.smartmanufacturing.controller;

import com.example.smartmanufacturing.model.Prediction;
import com.example.smartmanufacturing.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PredictionController {

    private final PredictionService predictionService;

    @Autowired
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/predictions")
    public ResponseEntity<List<Prediction>> getAllPredictions() {
        List<Prediction> predictions = predictionService.getAllPredictions();
        return new ResponseEntity<>(predictions, HttpStatus.OK);
    }

    @GetMapping("/predictions/{id}")
    public ResponseEntity<Prediction> getPredictionById(@PathVariable Long id) {
        return predictionService.getPredictionById(id)
                .map(prediction -> new ResponseEntity<>(prediction, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/machines/{machineId}/predictions")
    public ResponseEntity<List<Prediction>> getPredictionsByMachineId(@PathVariable Long machineId) {
        List<Prediction> predictions = predictionService.getPredictionsByMachineId(machineId);
        return new ResponseEntity<>(predictions, HttpStatus.OK);
    }

    @PostMapping("/predictions")
    public ResponseEntity<Prediction> createPrediction(@RequestBody Prediction prediction) {
        Prediction savedPrediction = predictionService.savePrediction(prediction);
        return new ResponseEntity<>(savedPrediction, HttpStatus.CREATED);
    }

    @DeleteMapping("/predictions/{id}")
    public ResponseEntity<Void> deletePrediction(@PathVariable Long id) {
        return predictionService.getPredictionById(id)
                .map(prediction -> {
                    predictionService.deletePrediction(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
} 