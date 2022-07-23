package com.smarttown.databatchaggregator.services;

import com.smarttown.databatchaggregator.repositories.ElectricityUsageRepository;
import com.smarttown.databatchaggregator.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class ProductMergingService {

    Logger logger = LoggerFactory.getLogger(ProductMergingService.class);
    private final ProductService productService;
    ElectricityUsageRepository repository;

    public ProductMergingService(ProductService productService, ElectricityUsageRepository repository) {
        this.productService = productService;
        this.repository = repository;
    }


    //this should retrieve sensors from sensor_information table
    //todo: add corresponding entity/repo/service for managing sensors
    //todo: add sensor_type table and store types there (stored in enum in data-emulator only for now)
    public List<String> getSensors(){
        logger.info("Sensors: {}", productService.getAllSensors());

        return productService.getAllSensors();
    }

    //todo: this one when user wants start merging from the timestamp that they finished last time
    public void merge(Integer periodToMerge, String sensorId) {
        //prolly we'll need to store timestamp of last merged data and get it here
        Date startDate = new Date();

        LocalDateTime mergeFrom = DateUtils.convertToLocalDateViaInstant(startDate);
        LocalDateTime mergeTo = mergeFrom.plusMinutes(periodToMerge);

        productService.mergeDataForPeriod(DateUtils.convertToDateViaInstant(mergeFrom),
                DateUtils.convertToDateViaInstant(mergeTo), sensorId);

    }

    public void merge(Integer periodToMerge, Date startDate, String sensorId) {

        LocalDateTime mergeFrom = DateUtils.convertToLocalDateViaInstant(startDate);
        LocalDateTime mergeTo = mergeFrom.plusMinutes(periodToMerge);

        productService.mergeDataForPeriod(DateUtils.convertToDateViaInstant(mergeFrom),
                DateUtils.convertToDateViaInstant(mergeTo), sensorId);

    }

    public void merge(Integer periodToMerge, Date startDate, List<String> sensorList) {

        for (String sensorId : sensorList) {
            try {
                merge(periodToMerge, startDate, sensorId);
            } catch (IndexOutOfBoundsException e) {
                logger.info("No records found between {} - {} for sensor {}", startDate, DateUtils.convertToLocalDateViaInstant(startDate).plusMinutes(periodToMerge), sensorId);
            }
        }
    }
}
