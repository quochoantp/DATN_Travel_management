package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTransactionMailDTO {

    private Long id;

    private Long userId;

    private String fullname;

    private String email;

    private String phoneNumber;

    private Integer sumPrice;

    private String nameTour;
}
