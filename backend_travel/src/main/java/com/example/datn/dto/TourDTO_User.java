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
public class TourDTO_User {
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

    public TourDTO_User(Long id, String code, String name, String shortDesc, Date startTime, Integer period, Long startPlaceId, String startPlaceName, Long endPlaceId, String endPlaceName, String mainImageUrl, Long guideId, String guideName, Integer placeOrderMax, Integer status, Long priceId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.shortDesc = shortDesc;
        this.startTime = startTime;
        this.period = period;
        this.startPlaceId = startPlaceId;
        this.startPlaceName = startPlaceName;
        this.endPlaceId = endPlaceId;
        this.endPlaceName = endPlaceName;
        this.mainImageUrl = mainImageUrl;
        this.guideId = guideId;
        this.guideName = guideName;
        this.placeOrderMax = placeOrderMax;
        this.status = status;
        this.priceId = priceId;
    }

    public TourDTO_User(Long id, String code, String name, String shortDesc, Date startTime, Integer period, String startPlaceName, String endPlaceName, String mainImageUrl, Integer placeOrderMax, Integer price, Float discount, Date startDateDiscount, Date endDateDiscount) {
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
        this.price = price;
        this.discount = discount;
        this.startDateDiscount = startDateDiscount;
        this.endDateDiscount = endDateDiscount;
    }
}
