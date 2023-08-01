package com.example.datn.dto;

import com.example.datn.entity.TouristOfOrders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractInfoDTO {
    private String fullname;
    private String email;
    private String phoneNumber;
    private String address;
    private String identityCard;

    public ContractInfoDTO(String fullname, String email, String phoneNumber, String address, String identityCard, Integer adultCount, Integer childrenCount, Integer kidCount, Integer sumPrice, String orderCode, String nameTour, Date startTime, Integer period, Integer adultPrice, Integer childrenPrice, Integer kidPrice) {
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.identityCard = identityCard;
        this.adultCount = adultCount;
        this.childrenCount = childrenCount;
        this.kidCount = kidCount;
        this.sumPrice = sumPrice;
        this.orderCode = orderCode;
        this.nameTour = nameTour;
        this.startTime = startTime;
        this.period = period;
        this.adultPrice = adultPrice;
        this.childrenPrice = childrenPrice;
        this.kidPrice = kidPrice;
    }

    private Integer adultCount;
    private Integer childrenCount;
    private Integer kidCount;
    private Integer sumPrice;
    private String orderCode;
    private List<TouristOfOrders> touristDTOList;

    private String nameTour;
    private Date startTime;
    private Integer period;

    private Integer adultPrice;
    private Integer childrenPrice;
    private Integer kidPrice;
}
