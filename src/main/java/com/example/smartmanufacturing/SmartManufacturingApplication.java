package com.example.smartmanufacturing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartManufacturingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartManufacturingApplication.class, args);
    }
} 