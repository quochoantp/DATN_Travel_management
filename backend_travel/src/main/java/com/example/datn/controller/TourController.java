package com.example.datn.controller;

import com.example.datn.dto.FilterTourDTO;
import com.example.datn.dto.TourDTO_User;
import com.example.datn.dto.TourDetailDTO;
import com.example.datn.dto.TourInfoDTO;
import com.example.datn.entity.*;
import com.example.datn.repository.PlaceAdvertismentRepository;
import com.example.datn.repository.TourRepository;
import com.example.datn.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class TourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private PlaceAdvertismentRepository placeAdvertismentRepository;

    @GetMapping("/tours")
    private ResponseEntity<List<Tour>> getTour(@RequestParam(name = "codeTour", required = false) String codeTour,
                                               @RequestParam(name = "nameTour", required = false) String nameTour){
        return new ResponseEntity<>(tourRepository.findAll(), HttpStatus.OK);
    }

    //danh sach tour
    @PostMapping("/tour-list")
    public ResponseEntity<List<TourDTO_User>> getTourList(@RequestBody FilterTourDTO filterTourDTO){
        return new ResponseEntity<>(tourService.getTourListFilter(filterTourDTO.getStartPlaceName(),filterTourDTO.getEndPlaceName(),filterTourDTO.getStartTime(), filterTourDTO.getFromPeriod(), filterTourDTO.getToPeriod(), filterTourDTO.getFromPrice(), filterTourDTO.getToPrice()), HttpStatus.OK);
    }

    @GetMapping("/tour-discount")
    public ResponseEntity<List<TourDTO_User>> getToursDiscount(){
        return new ResponseEntity<>(tourService.getToursDiscount(), HttpStatus.OK);
    }

    @GetMapping("/tour-detail/{id}")
    public ResponseEntity<TourDetailDTO> getTourDetail(@PathVariable Long id){
        return new ResponseEntity<>(tourService.getTourDetail(id), HttpStatus.OK);
    }

    @GetMapping("/place-advertisment")
    public ResponseEntity<List<PlaceAdvertisment>> getPlace(){
        return new ResponseEntity<>(placeAdvertismentRepository.findAll(), HttpStatus.OK);
    }
}
