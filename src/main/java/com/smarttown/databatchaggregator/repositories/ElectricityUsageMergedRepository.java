package com.smarttown.databatchaggregator.repositories;

import com.smarttown.databatchaggregator.entities.postgres.ElectricityUsageSensorMerged;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ElectricityUsageMergedRepository extends JpaRepository<ElectricityUsageSensorMerged, String> {


}
