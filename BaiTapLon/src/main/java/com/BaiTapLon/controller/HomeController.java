package com.BaiTapLon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class HomeController {
    
    @GetMapping("/home")
    public String Home(){
        return "Home";
    }

    @GetMapping("/homeAdmin")
    public String HomeAdmin(){
        return "HomeAdmin";
    }

    @GetMapping("/homeUser")
    public String HomeUser(){
        return "HomeUser";
    }

}
