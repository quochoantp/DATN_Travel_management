package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateUserDTO {
    private String fullName;
    private Integer rateUser;
    private String comment;
}
