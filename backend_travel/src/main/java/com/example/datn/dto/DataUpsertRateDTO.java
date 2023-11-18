package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataUpsertRateDTO {
    private Long userId;
    private String tourCode;
    private Integer rate;
    private String comment;

}
