package com.example.datn.controller;

import com.example.datn.entity.Place;
import com.example.datn.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping("/places")
    private ResponseEntity<List<Place>> getPlaceList(){
        return new ResponseEntity<>(placeService.getListPlace(), HttpStatus.OK);
    }
}
