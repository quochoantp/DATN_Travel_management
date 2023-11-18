package com.example.datn.service;

import com.example.datn.dto.DataUpsertRateDTO;
import com.example.datn.dto.RateResponeDTO;
import com.example.datn.dto.RateUserDTO;
import com.example.datn.entity.Rate;
import com.example.datn.entity.UserRate;
import com.example.datn.repository.RateRepository;
import com.example.datn.repository.UserRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private UserRateRepository userRateRepository;

    public RateResponeDTO findAllCommentByTourCode(String tourCode){
        RateResponeDTO rateResponeDTO = new RateResponeDTO();
        Optional<Rate> rateOptional = rateRepository.findByTourCode(tourCode);
        if(rateOptional.isPresent()){
            rateResponeDTO.setRateTotal(rateOptional.get().getRate());
            Optional<List<RateUserDTO>> userRateListOptional = userRateRepository.findByRateId(rateOptional.get().getId());
            if(userRateListOptional.isPresent()){
               rateResponeDTO.setRateUserDTO(userRateListOptional.get());
            }
        }
                return rateResponeDTO;
    }

    public void upsert(DataUpsertRateDTO dataUpsertRateDTO){
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        Optional<Rate> rateOpt =  rateRepository.findByTourCode(dataUpsertRateDTO.getTourCode());
        Rate rateSave = new Rate();
        Integer countComment = 0;
        if(rateOpt.isPresent()){
           countComment = userRateRepository.countTotalComment(rateOpt.get().getId());
           rateSave = rateOpt.get();
            UserRate userRate = new UserRate();
           Optional<UserRate> userRateOptional = userRateRepository.findByRateIdAndUserId(rateSave.getId(), dataUpsertRateDTO.getUserId());
           if(userRateOptional.isPresent()){
               userRate = userRateOptional.get();
               userRate.setRateUser(dataUpsertRateDTO.getRate());
               userRate.setComment(dataUpsertRateDTO.getComment());
           } else {
               userRate.setRateId(rateSave.getId());
               userRate.setUserId(dataUpsertRateDTO.getUserId());
               userRate.setRateUser(dataUpsertRateDTO.getRate());
               userRate.setComment(dataUpsertRateDTO.getComment());
           }
            userRateRepository.save(userRate);
            Float rateTotal = (float)((Float.valueOf(rateSave.getRate()) * countComment) + dataUpsertRateDTO.getRate()) / (countComment+1);
            rateSave.setRate(decimalFormat.format(rateTotal));
            rateRepository.save(rateSave);
        } else {
            Rate rate = new Rate();
            rate.setTourCode(dataUpsertRateDTO.getTourCode());
            rate.setRate(dataUpsertRateDTO.getRate().toString());
            rateSave = rateRepository.save(rate);
            UserRate userRate = new UserRate();
            userRate.setRateId(rateSave.getId());
            userRate.setUserId(dataUpsertRateDTO.getUserId());
            userRate.setRateUser(dataUpsertRateDTO.getRate());
            userRate.setComment(dataUpsertRateDTO.getComment());
            userRateRepository.save(userRate);
        }
    }
}
