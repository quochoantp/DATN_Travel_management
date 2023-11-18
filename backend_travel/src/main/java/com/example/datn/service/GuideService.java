package com.example.datn.service;

import com.example.datn.dto.GuideSelectBoxDTO;
import com.example.datn.dto.TourGuideAddDTO;
import com.example.datn.entity.Guide;
import com.example.datn.entity.TourGuide;
import com.example.datn.repository.GuideRepository;
import com.example.datn.repository.TourGuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GuideService {

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private TourGuideRepository tourGuideRepository;

    public List<Guide> getListGuideByNameAndPhone(String name, String phone){
        if(name == null || name.equals("null")){
            name = "";
        }
        if(phone == null || phone.equals("null")){
            phone = "";
        }
        List<Guide> guideList = new ArrayList<>();
        if(guideRepository.listGuide(name,phone).isPresent()){
            return guideRepository.listGuide(name,phone).get();
        }
        return guideList;
    }

    public Integer getMaxId(){
        return guideRepository.getMaxId();
    }

    public void addNewGuide(Guide g){
        guideRepository.save(g);
    }
    public void updateNewGuide(Guide g){
        guideRepository.save(g);
    }

    public List<GuideSelectBoxDTO> getGuideSelectBox(){
        List<GuideSelectBoxDTO> guideSelectBoxDTOList = new ArrayList<>();
        if(guideRepository.getListGuideSelectbox().isPresent()){
            return guideRepository.getListGuideSelectbox().get();
        }
        return guideSelectBoxDTOList;
    }

    public void addTourGuide(TourGuideAddDTO tourGuideAddDTO) throws Exception {
        if(tourGuideAddDTO.getTourId() == null){
          throw new Exception("Không truyền id tour");
        } else {
            if(tourGuideRepository.findByTourId(tourGuideAddDTO.getTourId()).isPresent()){
                tourGuideRepository.deleteByTourId(tourGuideAddDTO.getTourId());
            }
            if(!tourGuideAddDTO.getGuideId().isEmpty()){
                for(Long guide : tourGuideAddDTO.getGuideId()){
                    TourGuide tourGuide = new TourGuide();
                    tourGuide.setTourId(tourGuideAddDTO.getTourId());
                    tourGuide.setGuideId(guide);
                    tourGuide.setStatus(1);
                    tourGuide.setCreatedBy("admin");
                    tourGuide.setCreatedDate(new Date());
                    tourGuideRepository.saveAndFlush(tourGuide);
                }
            }

        }
    }
}
