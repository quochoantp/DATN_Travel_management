package com.example.datn.controller;

import com.example.datn.dto.DataAddTourDTO;
import com.example.datn.dto.DataUpsertRateDTO;
import com.example.datn.dto.RateResponeDTO;
import com.example.datn.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class RateController {

    @Autowired
    private RateService rateService;

    @PostMapping("/rate/upsert")
    public DataUpsertRateDTO upsertComment(@RequestBody DataUpsertRateDTO dataUpsertRateDTO) throws Exception {
        rateService.upsert(dataUpsertRateDTO);
        return dataUpsertRateDTO;
    }

    @GetMapping("/rate")
    public RateResponeDTO getCommentByTour(@RequestParam("tourCode") String tourCode) throws Exception {
        return rateService.findAllCommentByTourCode(tourCode);
    }
}
