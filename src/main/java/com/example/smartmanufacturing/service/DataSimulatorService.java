package com.example.smartmanufacturing.service;

import com.example.smartmanufacturing.model.Machine;
import com.example.smartmanufacturing.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class DataSimulatorService {
    
    private final MachineService machineService;
    private final SensorDataService sensorDataService;
    private final PredictionService predictionService;
    private final Random random = new Random();
    
    @Autowired
    public DataSimulatorService(
            MachineService machineService,
            SensorDataService sensorDataService,
            PredictionService predictionService) {
        this.machineService = machineService;
        this.sensorDataService = sensorDataService;
        this.predictionService = predictionService;
    }
    
    @Scheduled(fixedRate = 5000) // 每5秒生成一次数据
    public void simulateSensorData() {
        List<Machine> machines = machineService.getAllMachines();
        if (machines.isEmpty()) {
            // 如果还没有机器，不生成数据
            return;
        }
        
        machines.forEach(machine -> {
            // 为每台机器生成随机数据
            SensorData data = new SensorData();
            data.setMachine(machine);
            data.setTimestamp(LocalDateTime.now());
            
            // 生成50-100之间的随机温度
            double temperature = 50 + random.nextDouble() * 50;
            data.setTemperature(temperature);
            
            // 生成0-15之间的随机振动
            double vibration = random.nextDouble() * 15;
            data.setVibration(vibration);
            
            // 保存传感器数据
            SensorData savedData = sensorDataService.saveSensorData(data);
            
            // 检查数据是否触发预测
            predictionService.generatePrediction(savedData);
            
            // 根据传感器数据更新机器状态
            updateMachineStatus(machine, temperature, vibration);
        });
    }
    
    private void updateMachineStatus(Machine machine, double temperature, double vibration) {
        String status;
        
        if (temperature > 90 || vibration > 12) {
            status = "WARNING";
        } else if (temperature > 80 || vibration > 10) {
            status = "UNSTABLE";
        } else {
            status = "RUNNING";
        }
        
        machine.setStatus(status);
        machineService.saveMachine(machine);
    }
} 