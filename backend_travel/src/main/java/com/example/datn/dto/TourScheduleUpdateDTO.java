package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourScheduleUpdateDTO {

    private Integer id;

    private String alias;

    private String time;

    private String location;

    private Integer tourId;

    private String detail;
}
