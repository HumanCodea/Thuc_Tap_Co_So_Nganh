package com.BaiTapLon.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BaiTapLon.dto.FoodDTO;
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

    @GetMapping("/registerFood")
    public String RegisterFood(Model model){

        FoodDTO foodDTO = new FoodDTO();

        model.addAttribute("foodDTO", foodDTO);

        return "RegisterFood";
    }

    @PostMapping("/registerFood")
    public String SaveRegisterFood(@ModelAttribute FoodDTO foodDTO){

        MultipartFile file = foodDTO.getFoodFile();

        uploadFile(file);

        Food food = new Food();

        food.setNameFood(foodDTO.getNameFood());
        food.setPriceFood(foodDTO.getPriceFood());
        food.setFoodImage(file.getOriginalFilename());

        foodService.saveFood(food);

        return "redirect:/manageFood";
    }

    @GetMapping("/editFood/{id}")
    public String editFood(@ModelAttribute("id") int id, Model model){

        Food food = foodService.findFoodById(id);

        model.addAttribute("food", food);

        return "EditFood";
    }

    @PostMapping("/saveFood")
    public String SaveEditFood(@ModelAttribute Food food, @RequestParam("foodFile") MultipartFile foodImage){

        if (!foodImage.isEmpty()) {
            uploadFile(foodImage);
            food.setFoodImage(foodImage.getOriginalFilename());
        }

        foodService.saveFood(food);

        return "redirect:/manageFood";
    }

    @GetMapping("/deleteFood/{id}")
    public String deleteFood(@ModelAttribute("id") int id){
    
        foodService.deleteFood(id);

        return "redirect:/manageFood";
    }

    public void uploadFile(MultipartFile file){

        String fileName = file.getOriginalFilename();

        try {
            
            String uploadDir = "BaiTapLon/src/main/resources/static/image/";
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath();

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

    }

}
