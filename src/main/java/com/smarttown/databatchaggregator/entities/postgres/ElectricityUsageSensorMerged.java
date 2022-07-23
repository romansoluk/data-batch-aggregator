package com.smarttown.databatchaggregator.entities.postgres;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
@Table(name = "electricity_usage_sensor")
public class ElectricityUsageSensorMerged implements ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "sensor_id")
    private String sensorId;

    @Column(name = "product_used")
    private Double productUsed;

    @Column(name = "date_update")
    private Date dateSent;

}
