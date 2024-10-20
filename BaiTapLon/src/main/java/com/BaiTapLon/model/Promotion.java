package com.BaiTapLon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Promotion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int promotionId;

    @Column(nullable = false)
    @NotBlank(message = "This name promotion field can not be blank")
    private String namePromotion;

    @Column(nullable = false)
    @NotBlank(message = "This detail promotion field can not be blank")
    private String detailPromotion;

    @Column(nullable = false)
    @NotBlank(message = "This expirated field can not be blank")
    private String expirated;

}
