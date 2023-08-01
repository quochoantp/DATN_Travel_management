package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersHistoryDTO {
    private Long id;

    private Long userId;

    private String fullname;

    private String email;

    private String phoneNumber;

    private String address;

    private Integer adultCount;

    private Integer childrenCount;

    private Integer kidCount;

    private Date createdDate;

    private String status;

    private Long priceId;

    private Integer sumPrice;

    private String tourName;
}
