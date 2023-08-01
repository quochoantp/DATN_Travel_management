package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderInfoDTO {
    private String fullname;
    private String identityCard;
    private String email;
    private String dob;
    private String childrenCount;
    private String adultCount;
    private String kidCount;
    private String phoneNumber;
    private Long priceId;
    private Long sumPrice;
    private String userId;
    private String address;
}
