package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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

    public Promotion findPromotionById(int id){
        return promotionRepository.findById(id).get();
    }

    public void savePromotion(Promotion promotion){
        promotionRepository.save(promotion);
    }

    public void deletePromotion(int id){
        promotionRepository.deleteById(id);
    }

}
