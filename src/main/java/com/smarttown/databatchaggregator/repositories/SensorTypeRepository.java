package com.smarttown.databatchaggregator.repositories;

import com.smarttown.databatchaggregator.entities.postgres.SensorType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeRepository extends JpaRepository<SensorType, String> {

    SensorType getSensorTypeBySensorTypeName(String sensorTypeName);

}
