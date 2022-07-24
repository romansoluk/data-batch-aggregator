package com.smarttown.databatchaggregator.services;


import com.smarttown.databatchaggregator.entities.postgres.SensorType;
import com.smarttown.databatchaggregator.repositories.SensorTypeRepository;

import org.springframework.stereotype.Service;

@Service
public class SensorTypeService {

    private final SensorTypeRepository sensorTypeRepository;


    SensorTypeService(SensorTypeRepository sensorTypeRepository) {
        this.sensorTypeRepository = sensorTypeRepository;
    }


    /**
     * Get sensor type by name
     *
     * @param sensorTypeName
     * @return sensorType
     */
    public SensorType getSensorType(String sensorTypeName) {
        return sensorTypeRepository.getSensorTypeBySensorTypeName(sensorTypeName);
    }
}
