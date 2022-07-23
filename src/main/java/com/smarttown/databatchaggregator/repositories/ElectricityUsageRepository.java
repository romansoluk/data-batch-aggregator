package com.smarttown.databatchaggregator.repositories;

import com.smarttown.databatchaggregator.entities.mongo.ElectricityUsageSensor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ElectricityUsageRepository extends MongoRepository<ElectricityUsageSensor, String> {

 List<ElectricityUsageSensor> getElectricityUsageSensorByDateSentBetweenAndAndSensorId(Date startDate, Date endDate, String sensorId);

}
