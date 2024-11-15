package com.BaiTapLon.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.auth.entities.Custormer;
import com.BaiTapLon.auth.service.CustormerService;

@Controller
@RequestMapping(path = "")
public class UserController {

    @Autowired
    private CustormerService custormerService;
    
    @GetMapping("/login")
    public String LoginScreen(){
        return "Login";
    }
   
    @GetMapping("/register")
    public String Register(){
        return "Register";
    }

    @PostMapping("/saveUser")
    public String SaveUser(@ModelAttribute Custormer custormer){
        custormerService.saveCustormer(custormer);
        return "redirect:/login?successfully";
    }

}
