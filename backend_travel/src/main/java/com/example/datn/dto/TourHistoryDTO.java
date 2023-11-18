package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourHistoryDTO {
    private Long id;
    private String tourCode;
    private String tourName;
    private Integer status;
}
