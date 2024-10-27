package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.Promotion;
import com.BaiTapLon.repository.PromotionRepository;

@Service
public class PromotionService {
    
    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> getAllPromotion(){
        return promotionRepository.findAll();
    }

}
