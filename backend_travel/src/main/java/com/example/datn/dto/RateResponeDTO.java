package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateResponeDTO {
    private String rateTotal;
    private List<RateUserDTO> rateUserDTO;
}
