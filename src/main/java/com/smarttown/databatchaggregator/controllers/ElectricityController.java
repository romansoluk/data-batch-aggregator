package com.smarttown.databatchaggregator.controllers;

import com.smarttown.databatchaggregator.dto.ProductMergingRequestDto;
import com.smarttown.databatchaggregator.services.ProductMergingService;
import com.smarttown.databatchaggregator.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("electricity")
public class ElectricityController {


    @Autowired
    private ProductMergingService mergingService;

    private boolean isElectricityUsageReadyToMerge = false;


    @PostMapping("/is-ready-to-merge/{isMergable}")
    public String isMergable(@PathVariable Integer isMergable){
        if (isMergable==1){
            isElectricityUsageReadyToMerge = true;
            return "Ready to merge!";
        }else {
            isElectricityUsageReadyToMerge=false;
            return "Merging is completed!";
        }
    }

    @PostMapping("/merge")
    public String merge(@RequestBody ProductMergingRequestDto mergingRequestDto) throws InterruptedException {

        List<String> ids = mergingService.getSensors();

        //time format 2022-07-23T09:27:30
        // todo: Sun Jul 24 04:22:47 EEST 2022 - date from mongo -> 2022-07-24T16:22:47  current date
        Date date = DateUtils.convertToDateViaInstant(LocalDateTime.parse(mergingRequestDto.getStartDate()));
        Integer periodToMerge = mergingRequestDto.getPeriodToMerge();
        
        while (isElectricityUsageReadyToMerge) {
            date = DateUtils.convertToDateViaInstant(DateUtils.convertToLocalDateViaInstant(date).plusMinutes(periodToMerge));

            mergingService.merge(periodToMerge, date, ids);

            //todo: find a better way
            Thread.sleep(1000L * 60 * periodToMerge);
        }

        return "Ready!";
    }
}
