package com.BaiTapLon.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.auth.service.CustormerService;

@Controller
@RequestMapping(path = "")
public class HomeController {

    @Autowired
    private CustormerService custormerService;
    
    @GetMapping("/home")
    public String Home(){
        return "Home";
    }

    @GetMapping("/homeUser")
    public String HomeUser(Model model){

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        model.addAttribute("name", username);

        String role = getRole();

        if (role.equals("ADMIN")){
            return "HomeAdmin";
        }

        return "HomeUser";
    }

    public String getUsername(){
        String email = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            email = userDetails.getUsername();

        }

        return email;

    }

    public String getRole(){

        Collection<? extends GrantedAuthority> authorities = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();


            authorities = userDetails.getAuthorities();
        }

        String role = null;

        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }

        return role;

    }

}
