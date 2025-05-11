package com.example.smartmanufacturing.repository;

import com.example.smartmanufacturing.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findByMachineIdOrderByTimestampDesc(Long machineId);
} 