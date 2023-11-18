package com.example.datn.dto;

import lombok.Data;

import java.util.List;

@Data
public class DashboardFilterDTO {
    List<Integer> statusOrderCount;
    List<String> dayList;
    List<Integer> countOfDay;
}
