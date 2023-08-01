package com.example.datn.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseOrderDTO {
    private String isPaymentOnline;
    private OrderInfoDTO orderInfoDTO;
    private List<TouristDTO> touristDTOList;
}
