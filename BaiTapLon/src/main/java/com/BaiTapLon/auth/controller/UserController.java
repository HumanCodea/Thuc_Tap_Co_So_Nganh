package com.BaiTapLon.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class UserController {
    
    @GetMapping("/login")
    public String LoginScreen(){
        return "Login";
    }

}
