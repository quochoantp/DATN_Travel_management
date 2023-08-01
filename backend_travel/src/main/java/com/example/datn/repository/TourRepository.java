package com.example.datn.repository;


import com.example.datn.dto.TourDTO_User;
import com.example.datn.dto.TourDetailDTO;
import com.example.datn.dto.TourInfoDTO;
import com.example.datn.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {

    //tour giam gia
    @Query("select distinct new com.example.datn.dto.TourDTO_User" +
            "(t.id,t.code,t.name,t.shortDesc,t.startTime,t.period,t.startPlaceName,t.endPlaceName,t.mainImageUrl,t.placeOrderMax,tp.adult,d.discount,d.startDate,d.endDate) " +
            "from Tour t, Price p, TourPrice tp, Discount d " +
            "where  t.id = p.tourId and p.tourPriceId = tp.id and p.discountId = d.id")
    List<TourDTO_User> getToursDiscount();


    @Query("select distinct new com.example.datn.dto.TourDTO_User" +
            "(t.id,t.code,t.name,t.shortDesc,t.startTime,t.period,t.startPlaceName,t.endPlaceName,t.mainImageUrl,t.placeOrderMax,tp.adult,d.discount,d.startDate,d.endDate) " +
            "from Tour t join Price p on t.id = p.tourId join TourPrice tp on p.tourPriceId = tp.id left join Discount d on p.discountId = d.id " +
            "where t.status = 1 and t.isdel = 0" +
            "and (?1 is null or (lower(t.startPlaceName) like lower(concat('%',?1,'%')))) and (?2 is null or lower(t.endPlaceName) like lower(concat('%',?2,'%'))) " +
            "and (t.startTime = ?3 or ?3 is null) " +
            "and (t.period >= ?4 or ?4 is null) and (t.period <= ?5 or ?5 is null) " +
            "and (tp.adult >= ?6 or ?6 is null) and (tp.adult <=?7 or ?7 is null) " +
            "order by t.startTime desc ")
    List<TourDTO_User> getTourListFilter(String startPlaceId, String endPlaceId, Date startTime, Integer fromPeriod, Integer toPeriod, Integer fromPrice, Integer toPrice);


    @Query("select distinct new com.example.datn.dto.TourDetailDTO" +
            "(t.id,t.code,t.name,t.shortDesc,t.startTime,t.period,t.startPlaceName,t.endPlaceName,t.mainImageUrl,t.placeOrderMax,t.status,ti.image100,ti.image50,ti.image251,ti.image252, p.id, d.discount) " +
            "from  Tour t left join TourImage ti on t.id = ti.tourId left join Price p on p.tourId = t.id left join Discount d on d.id = p.discountId " +
            "where t.id = ?1")
    TourDetailDTO getTourById(Long id);


}
