package com.BaiTapLon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.model.Promotion;
import com.BaiTapLon.service.PromotionService;

@Controller
@RequestMapping(path = "")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;
    
    @GetMapping("/managePromotion")
    public String ManagePromotion(Model model){

        List<Promotion> promotions = promotionService.getAllPromotion();

        model.addAttribute("Promotion", promotions);

        return "ManagePromotion";
    }


}
