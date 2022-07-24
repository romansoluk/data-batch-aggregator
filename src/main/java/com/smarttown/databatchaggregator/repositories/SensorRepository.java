package com.smarttown.databatchaggregator.repositories;

import com.smarttown.databatchaggregator.entities.postgres.Sensor;
import com.smarttown.databatchaggregator.entities.postgres.SensorType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {

    List<Sensor> getSensorsBySensorType(SensorType sensorType);

}
