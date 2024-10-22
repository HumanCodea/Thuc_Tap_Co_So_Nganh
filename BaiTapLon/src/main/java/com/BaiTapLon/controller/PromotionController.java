package com.BaiTapLon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class PromotionController {
    
    @GetMapping("/managePromotion")
    public String ManagePromotion(){
        return "ManagePromotion";
    }


}
