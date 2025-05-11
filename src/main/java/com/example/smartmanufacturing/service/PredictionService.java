package com.example.smartmanufacturing.service;

import com.example.smartmanufacturing.model.Machine;
import com.example.smartmanufacturing.model.Prediction;
import com.example.smartmanufacturing.model.SensorData;
import com.example.smartmanufacturing.repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PredictionService {

    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionService(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    public List<Prediction> getAllPredictions() {
        return predictionRepository.findAll();
    }

    public List<Prediction> getPredictionsByMachineId(Long machineId) {
        return predictionRepository.findByMachineId(machineId);
    }

    public Optional<Prediction> getPredictionById(Long id) {
        return predictionRepository.findById(id);
    }

    public Prediction savePrediction(Prediction prediction) {
        return predictionRepository.save(prediction);
    }

    public void deletePrediction(Long id) {
        predictionRepository.deleteById(id);
    }
    
    // 根据传感器数据生成预测的方法
    public Prediction generatePrediction(SensorData sensorData) {
        // 简单的基于规则的预测演示
        if (sensorData.getVibration() > 10.0) {
            Prediction prediction = new Prediction();
            prediction.setMachine(sensorData.getMachine());
            prediction.setPredictedFailureTime(LocalDateTime.now().plusHours(2));
            prediction.setConfidence(0.8);
            return predictionRepository.save(prediction);
        } else if (sensorData.getTemperature() > 80.0) {
            Prediction prediction = new Prediction();
            prediction.setMachine(sensorData.getMachine());
            prediction.setPredictedFailureTime(LocalDateTime.now().plusHours(4));
            prediction.setConfidence(0.6);
            return predictionRepository.save(prediction);
        }
        
        // 不需要预测
        return null;
    }
} 