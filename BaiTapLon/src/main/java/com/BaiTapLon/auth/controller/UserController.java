package com.BaiTapLon.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String SaveUser(@ModelAttribute Custormer custormer, RedirectAttributes redirectAttributes){
        custormerService.saveCustormer(custormer);
        // Khi sử dụng RedirectAttributes.addFlashAttribute, dữ liệu sẽ chỉ tồn tại trong request tiếp theo và không hiển thị trong URL
        redirectAttributes.addFlashAttribute("successfully", true);
        return "redirect:/login";
    }

    @GetMapping("forgetPassword")
    public String ForgotPassword(){


        return "ForgetPassword";
    }

    @PostMapping("/verifyEmail")
    public String VerifyEmail(@ModelAttribute("email") String email, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute(email, true);

        return "ForgetPasswordOtp";
    }

    @PostMapping("/verifyOtp")
    public String VerifyOtp(){



        return "ChangePassword";
    }

}
