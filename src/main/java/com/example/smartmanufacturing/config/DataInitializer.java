package com.example.smartmanufacturing.config;

import com.example.smartmanufacturing.model.Machine;
import com.example.smartmanufacturing.model.User;
import com.example.smartmanufacturing.service.MachineService;
import com.example.smartmanufacturing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final MachineService machineService;

    @Autowired
    public DataInitializer(UserService userService, MachineService machineService) {
        this.userService = userService;
        this.machineService = machineService;
    }

    @Override
    public void run(String... args) {
        // 创建管理员用户
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add("ADMIN");
        admin.setRoles(adminRoles);
        userService.saveUser(admin);
        
        // 创建操作员用户
        User operator = new User();
        operator.setUsername("operator");
        operator.setPassword("operator");
        Set<String> operatorRoles = new HashSet<>();
        operatorRoles.add("OPERATOR");
        operator.setRoles(operatorRoles);
        userService.saveUser(operator);
        
        // 创建示例机器
        createSampleMachines();
    }
    
    private void createSampleMachines() {
        String[] machineTypes = {"Drilling", "Milling", "Lathe", "Assembly", "Packaging"};
        
        for (int i = 1; i <= 5; i++) {
            Machine machine = new Machine();
            machine.setName("Machine " + i);
            machine.setType(machineTypes[i - 1]);
            machine.setStatus("RUNNING");
            machineService.saveMachine(machine);
        }
    }
} 