package com.example.datn.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentResDTO{
    private String status;
    private String message;
    private String url;
}
