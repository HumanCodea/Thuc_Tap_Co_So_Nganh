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
public class PromotionDTO {
    
    private int promotionId;

    private String namePromotion;

    private String detailPromotion;

    private String expirated;

    private MultipartFile promotionFile;

}
