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

import com.BaiTapLon.dto.PromotionDTO;
import com.BaiTapLon.model.Promotion;
import com.BaiTapLon.service.PromotionService;

@Controller
@RequestMapping(path = "")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;
    
    @GetMapping("/managePromotion")
    public String ManagePromotion(Model model){

        List<Promotion> promotions = promotionService.getAllPromotion();

        model.addAttribute("Promotion", promotions);

        return "ManagePromotion";
    }

    @GetMapping("/registerPromotion")
    public String RegisterPromotion(Model model){

        PromotionDTO promotionDTO = new PromotionDTO();

        model.addAttribute("promotionDTO", promotionDTO);

        return "RegisterPromotion";
    }

    @PostMapping("/registerPromotion")
    public String SaveRegisterPromotion(@ModelAttribute PromotionDTO promotionDTO){

        MultipartFile file = promotionDTO.getPromotionFile();

        uploadFile(file);

        Promotion promotion = new Promotion();

        promotion.setNamePromotion(promotionDTO.getNamePromotion());
        promotion.setExpirated(promotionDTO.getExpirated());
        promotion.setDetailPromotion(promotionDTO.getDetailPromotion());
        promotion.setPromotionImage(file.getOriginalFilename());

        promotionService.savePromotion(promotion);

        return "redirect:/managePromotion";
    }

    @GetMapping("/editPromotion/{id}")
    public String EditPromotion(@ModelAttribute("id") int id, Model model){

        Promotion promotion = promotionService.findPromotionById(id);

        model.addAttribute("promotion", promotion);

        return "EditPromotion";
    }

    @PostMapping("/savePromotion")
    public String SaveEditPromotion(@ModelAttribute Promotion promotion, @RequestParam("promotionFile") MultipartFile promotionImage){

        if(!promotionImage.isEmpty()){
            uploadFile(promotionImage);
            promotion.setPromotionImage(promotionImage.getOriginalFilename());
        }

        promotionService.savePromotion(promotion);

        return "redirect:/managePromotion";
    }

    @GetMapping("/deletePromtion/{id}")
    public String deletePromtion(@ModelAttribute("id") int id){
        promotionService.deletePromotion(id);
        return "redirect:/managePromotion";

    }

    public void uploadFile(MultipartFile file){

        String fileName = file.getOriginalFilename();

        try {

            String uploadDir = "BaiTapLon/src/main/resources/static/image/";
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath();

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            }
            
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

    }

}
