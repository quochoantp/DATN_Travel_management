package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TourGuideAddDTO {
    private Long tourId;
    private List<Long> guideId;
}
