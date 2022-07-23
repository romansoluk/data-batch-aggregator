package com.smarttown.databatchaggregator.services;

import com.smarttown.databatchaggregator.entities.postgres.ProductEntity;

import java.util.Date;
import java.util.List;

public interface ProductService {

    ProductEntity mergeDataForPeriod(Date startDate, Date endDate, String sensorId);

    List<String> getAllSensors();
}
