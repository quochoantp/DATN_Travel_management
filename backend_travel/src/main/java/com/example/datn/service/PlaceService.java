package com.example.datn.service;

import com.example.datn.entity.Place;
import com.example.datn.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> getListPlace(){
        List<Place> placeList = new ArrayList<>();
        if(placeRepository.placeList().isPresent()){
            return placeRepository.placeList().get();
        }
        return placeList;
    }
}
