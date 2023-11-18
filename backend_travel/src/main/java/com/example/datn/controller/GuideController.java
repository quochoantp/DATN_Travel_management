package com.example.datn.controller;

import com.example.datn.dto.GuideSelectBoxDTO;
import com.example.datn.dto.TourGuideAddDTO;
import com.example.datn.entity.Guide;
import com.example.datn.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class GuideController {

    @Autowired
    private GuideService guideService;

    @GetMapping("/guides")
    public List<Guide> getGuideList(@RequestParam(name = "nameGuide" , required = false) String name,
                                    @RequestParam(name = "phoneGuide", required = false) String phone){
        return guideService.getListGuideByNameAndPhone(name,phone);
    };

    @GetMapping("/guides/maxid")
    public Integer getGuideList(){
        return guideService.getMaxId();
    };

    @GetMapping("/guide-select-box")
    public List<GuideSelectBoxDTO> getGuideSelectBoxDTO(){
        return guideService.getGuideSelectBox();
    }

    @PostMapping("/upsert-guide-tour")
    public void getGuideSelectBoxDTO(@RequestBody TourGuideAddDTO tourGuideAddDTO) throws Exception {
        guideService.addTourGuide(tourGuideAddDTO);
    }

    @PostMapping("/guides")
    public void addNewGuide(@RequestBody Guide guide){
        guideService.addNewGuide(guide);
    }

    @PostMapping("/guides/update")
    public void updateGuide(@RequestBody Guide guide){
        guideService.updateNewGuide(guide);
    }
}
