package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestBodyDTO {
    private String amount;
    private String bankCode;
    private String language;
}
