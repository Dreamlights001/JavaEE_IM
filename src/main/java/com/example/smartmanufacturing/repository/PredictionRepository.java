package com.example.smartmanufacturing.repository;

import com.example.smartmanufacturing.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction> findByMachineId(Long machineId);
} 