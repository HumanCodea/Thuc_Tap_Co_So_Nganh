package com.BaiTapLon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class ScreenMovieCon {
    
    @GetMapping("/manageScreeningMovie")
        public String ManageScreeningMovie(){
            return "ScreeningMovie";
        }

}
