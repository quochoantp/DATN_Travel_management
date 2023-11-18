package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourScheduleDTO {
    private String alias;

    private String time;

    private String location;

    private String detail;
}
