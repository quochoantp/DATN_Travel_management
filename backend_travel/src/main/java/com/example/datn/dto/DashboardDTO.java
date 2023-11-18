package com.example.datn.dto;

import lombok.Data;

import java.util.List;

@Data
public class DashboardDTO {

    private List<Integer> monthlyRevenueList;
    private List<Integer> quaterlyRevenueList;
    private Integer totalTour;
    private Integer totalMember;
}
