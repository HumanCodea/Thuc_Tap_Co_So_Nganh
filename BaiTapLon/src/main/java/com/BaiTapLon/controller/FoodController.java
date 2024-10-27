package com.BaiTapLon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.model.Food;
import com.BaiTapLon.service.FoodService;

@Controller
@RequestMapping(path = "")
public class FoodController {

    @Autowired
    private FoodService foodService;
    
    @GetMapping("/manageFood")
        public String ManageFood(Model model){

            List<Food> foods = foodService.getAllFood();

            model.addAttribute("Food", foods);

            return "ManageFood";
        }



}
