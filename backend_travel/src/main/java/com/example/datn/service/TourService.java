package com.example.datn.service;

import com.example.datn.dto.*;
import com.example.datn.entity.*;
import com.example.datn.repository.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourPriceRepository tourPriceRepository;

    @Autowired
    private TourScheduleRepository tourScheduleRepository;

    public List<TourDTO_User> getToursDiscount(){
        return tourRepository.getToursDiscount();
    }

    public List<TourDTO_User> getTourListFilter(String startPLaceName, String endPlaceName, Date startTime, Integer fromPeriod, Integer toPeriod, Integer fromPrice, Integer toPrice){
        return tourRepository.getTourListFilter(startPLaceName, endPlaceName, startTime, fromPeriod, toPeriod, fromPrice, toPrice);
    }

    public TourDetailDTO getTourDetail(Long id){
        TourDetailDTO tour = tourRepository.getTourById(id);
        Optional<TourPrice> tourPriceOptional = tourPriceRepository.getTourPriceByTourId(id);
        tourPriceOptional.ifPresent(tour::setTourPrice);
        Optional<List<TourSchedule>> tourScheduleListOpt = tourScheduleRepository.getTourScheduleByTourId(id);
        tourScheduleListOpt.ifPresent(tour::setTourScheduleList);
        return tour;
    }

}
