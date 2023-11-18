package com.example.datn.dto;

import com.example.datn.entity.TouristOfOrders;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class OrdersListDTO {
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

    private Date updatedDate;

    private String status;

    private Long priceId;

    private Integer sumPrice;

    private String orderCode;

    private String identityCard;

    List<TouristDTO> touristDTOList;
}
