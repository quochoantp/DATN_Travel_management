package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJwtResAdminDTO {
    private Long id;
    private String jwt;
    private String roleCode;
}
