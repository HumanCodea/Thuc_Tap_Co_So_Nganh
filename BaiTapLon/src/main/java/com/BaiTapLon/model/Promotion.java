package com.BaiTapLon.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

    @Column(nullable = false)
    @NotBlank(message = "This promotion image field can not be blank")
    private String promotionImage;

    @OneToMany(mappedBy = "promotion")
    private List<Bill> bills;

}
