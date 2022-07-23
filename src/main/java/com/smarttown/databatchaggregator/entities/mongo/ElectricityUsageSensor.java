package com.smarttown.databatchaggregator.entities.mongo;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("electricityusagesensors")
public class ElectricityUsageSensor {

    @Indexed()
    private String recordId;
    private String sensorId;
    private Double productUsed;
    private Date dateSent;
}
