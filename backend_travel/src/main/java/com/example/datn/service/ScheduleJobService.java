package com.example.datn.service;

import com.example.datn.entity.Discount;
import com.example.datn.entity.Tour;
import com.example.datn.repository.DiscountRepository;
import com.example.datn.repository.PriceRepository;
import com.example.datn.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleJobService {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    PriceRepository priceRepository;

    @Scheduled(cron = "${status-tour-cron}")
    public void updateStatusTour(){
        List<Tour> tourList = tourRepository.findAll();
        for(Tour tour : tourList){
            if(tour.getStartTime().after(new Date())){
                if(tour.getPlaceOrderMax() == 0){
                    tourRepository.updateStatusTour(tour. getId(),3);
                } else {
                    tourRepository.updateStatusTour(tour.getId(),1);
                }
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(tour.getStartTime());
                calendar.add(Calendar.DAY_OF_MONTH, tour.getPeriod());
                Date endDate = calendar.getTime();
                if(endDate.before(new Date())){
                    tourRepository.updateStatusTour(tour.getId(),4);
                } else {
                    tourRepository.updateStatusTour(tour.getId(),2);
                }
            }
        }

    }

    @Scheduled(cron = "${status-discount-cron}")
    public void updateStatusDiscount(){
     List<Discount> discountList = discountRepository.findAll();
     for(Discount discount : discountList){
         if(discount.getEndDate().before(new Date())){
             priceRepository.updateDiscountForTour(discount.getId(),null);
         }
        }
    }
}
