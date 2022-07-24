package com.smarttown.databatchaggregator.services;

import com.smarttown.databatchaggregator.entities.postgres.Sensor;
import com.smarttown.databatchaggregator.repositories.SensorRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorTypeService sensorTypeService;

    public SensorService(SensorRepository sensorRepository, SensorTypeService sensorTypeService) {
        this.sensorRepository = sensorRepository;
        this.sensorTypeService = sensorTypeService;
    }

    public List<String> getSensorsByType(String type) {
        return sensorRepository.getSensorsBySensorType(sensorTypeService.getSensorType(type)).stream().map(Sensor::getSensorId).collect(Collectors.toList());
    }


}
