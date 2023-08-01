package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilterTourDTO {
    private String startPlaceName;
    private String endPlaceName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    private Integer fromPeriod;
    private Integer toPeriod;
    private Integer fromPrice;
    private Integer toPrice;
}
