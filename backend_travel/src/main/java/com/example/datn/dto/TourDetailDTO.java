package com.example.datn.dto;

import com.example.datn.entity.TourPrice;
import com.example.datn.entity.TourSchedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDetailDTO {
    private Long id;
    private String code;
    private String name;
    private String shortDesc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Date startTime;

    private Integer period;
    private Long startPlaceId;
    private String startPlaceName;
    private Long endPlaceId;
    private String endPlaceName;
    private String mainImageUrl;
    private Long guideId;
    private String guideName;
    private Integer placeOrderMax;
    private Integer status;
    private TourPrice tourPrice;
    private List<TourSchedule> tourScheduleList;
    private Long priceId;
    private Integer price;
    private Float discount;
    private Date startDateDiscount;
    private Date endDateDiscount;
    private String image100;
    private String image50;
    private String image251;
    private String image252;

    public TourDetailDTO(Long id, String code, String name, String shortDesc, Date startTime, Integer period, String startPlaceName, String endPlaceName, String mainImageUrl, Integer placeOrderMax, Integer status, String image100,String image50,String image251,String image252, Long priceId, Float discount) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.shortDesc = shortDesc;
        this.startTime = startTime;
        this.period = period;
        this.startPlaceName = startPlaceName;
        this.endPlaceName = endPlaceName;
        this.mainImageUrl = mainImageUrl;
        this.placeOrderMax = placeOrderMax;
        this.status = status;
        this.image100 = image100;
        this.image50 = image50;
        this.image251 = image251;
        this.image252 = image252;
        this.priceId = priceId;
        this.discount = discount;
    }
}
