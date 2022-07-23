package com.smarttown.databatchaggregator.services;

import com.smarttown.databatchaggregator.entities.mongo.ElectricityUsageSensor;
import com.smarttown.databatchaggregator.entities.postgres.ElectricityUsageSensorMerged;
import com.smarttown.databatchaggregator.entities.postgres.ProductEntity;
import com.smarttown.databatchaggregator.repositories.ElectricityUsageMergedRepository;
import com.smarttown.databatchaggregator.repositories.ElectricityUsageRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectricityUsageService implements ProductService {

    private final ElectricityUsageRepository usageRepository;
    private final ElectricityUsageMergedRepository usageMergedRepository;
    private final ModelMapper mapper;

    public ElectricityUsageService(ElectricityUsageRepository usageRepository,
                                   ElectricityUsageMergedRepository usageMergedRepository,
                                   ModelMapper mapper){
        this.usageRepository = usageRepository;
        this.usageMergedRepository = usageMergedRepository;
        this.mapper = mapper;
    }

    public List<String> getAllSensors(){
        return usageMergedRepository.findAll().stream().map(ElectricityUsageSensorMerged::getSensorId).distinct().collect(Collectors.toList());
    }

    public ProductEntity mergeDataForPeriod(Date startDate, Date endDate, String sensorId){
        List<ElectricityUsageSensor> data = usageRepository.getElectricityUsageSensorByDateSentBetweenAndAndSensorId(startDate, endDate, sensorId);
        data.sort(Comparator.comparing(ElectricityUsageSensor::getDateSent, Comparator.naturalOrder()));
        usageMergedRepository.save(mapper.map(data.get(data.size()-1), ElectricityUsageSensorMerged.class));

        return mapper.map(data.get(data.size()-1), ElectricityUsageSensorMerged.class);
    }

}

