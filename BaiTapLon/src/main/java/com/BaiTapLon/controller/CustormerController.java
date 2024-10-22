package com.BaiTapLon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class CustormerController {
    
    @GetMapping("/manageCustormer")
    public String ManageCustormer(){
        return "ManageCustormer";
    }

}
