package com.BaiTapLon.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FoodDTO {
    
    private int foodId;

    private String nameFood;

    private int priceFood;

    private MultipartFile foodFile;

}
