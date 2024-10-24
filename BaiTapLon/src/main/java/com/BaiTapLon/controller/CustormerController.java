package com.BaiTapLon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.auth.entities.Custormer;
import com.BaiTapLon.auth.service.CustormerService;
import com.BaiTapLon.auth.util.UserRoles;

@Controller
@RequestMapping(path = "")
public class CustormerController {

    @Autowired
    private CustormerService custormerService;
    
    @GetMapping("/manageCustormer")
    public String ManageCustormer(Model model){

        List<Custormer> list = custormerService.getAllCustormer();
        List<Custormer> custormers = new ArrayList<>();
        UserRoles role = UserRoles.USER;
        
        for(Custormer c : list){
            if (c.getRoles().equals(role)){
                custormers.add(c);
            }
        }

        model.addAttribute("Custormer", custormers);

        return "ManageCustormer";
    }

}
