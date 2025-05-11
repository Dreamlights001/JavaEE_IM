package com.example.smartmanufacturing.controller;

import com.example.smartmanufacturing.model.SensorData;
import com.example.smartmanufacturing.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @GetMapping("/sensorData")
    public ResponseEntity<List<SensorData>> getAllSensorData() {
        List<SensorData> sensorDataList = sensorDataService.getAllSensorData();
        return new ResponseEntity<>(sensorDataList, HttpStatus.OK);
    }

    @GetMapping("/machines/{machineId}/sensorData")
    public ResponseEntity<List<SensorData>> getSensorDataByMachineId(@PathVariable Long machineId) {
        List<SensorData> sensorDataList = sensorDataService.getSensorDataByMachineId(machineId);
        return new ResponseEntity<>(sensorDataList, HttpStatus.OK);
    }

    @GetMapping("/sensorData/{id}")
    public ResponseEntity<SensorData> getSensorDataById(@PathVariable Long id) {
        return sensorDataService.getSensorDataById(id)
                .map(sensorData -> new ResponseEntity<>(sensorData, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/machines/{machineId}/sensorData")
    public ResponseEntity<SensorData> createSensorData(@PathVariable Long machineId, @RequestBody SensorData sensorData) {
        // 机器应该在请求体中设置
        SensorData savedSensorData = sensorDataService.saveSensorData(sensorData);
        return new ResponseEntity<>(savedSensorData, HttpStatus.CREATED);
    }

    @DeleteMapping("/sensorData/{id}")
    public ResponseEntity<Void> deleteSensorData(@PathVariable Long id) {
        return sensorDataService.getSensorDataById(id)
                .map(sensorData -> {
                    sensorDataService.deleteSensorData(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
} 