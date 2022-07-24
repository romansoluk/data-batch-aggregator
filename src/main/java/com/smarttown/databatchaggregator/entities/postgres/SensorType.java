package com.smarttown.databatchaggregator.entities.postgres;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "SENSOR_TYPE", indexes = @Index(name = "sensor_type_name_ui", columnList = "sensor_type_name", unique = true))
public class SensorType {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "sensor_type_id")
    private Long sensorTypeId;

    @Column(name = "sensor_type_name")
    private String sensorTypeName;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_type_id")
    private List<Sensor> sensor;

    //TODO: override equals using sensorId
}
