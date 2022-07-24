package com.smarttown.databatchaggregator.entities.postgres;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SENSOR_INFORMATION")
public class Sensor {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String sensorId;

    @Column
    private String sensorName;
    @Column
    private LocalDateTime dateInstalled;
    @Column
    private String manufacturerCompany;

    //add house entity/repo/service as well?
    @Column(name = "house_id")
    private String houseId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_type_id", foreignKey = @ForeignKey(name = "sensor_type_fk"), referencedColumnName = "sensor_type_id")
    private SensorType sensorType;

    //TODO: override equals using sensorId
}
