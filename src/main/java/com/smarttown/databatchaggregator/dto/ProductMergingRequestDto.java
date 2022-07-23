package com.smarttown.databatchaggregator.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductMergingRequestDto {

    private Integer periodToMerge;

    //should be this way - don't know how to pass date in Postman
    //private LocalDateTime startDate;

    private String startDate;

}
