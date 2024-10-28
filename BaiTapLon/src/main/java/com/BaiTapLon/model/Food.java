package com.BaiTapLon.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Food {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;

    @Column(nullable = false)
    @NotBlank(message = "This name food field can not be blank")
    private String nameFood;

    private int priceFood;

    @Column(nullable = false)
    @NotBlank(message = "This food image field can not be blank")
    private String foodImage;

    @ManyToMany
    @JoinTable(
        name = "bill_food",
        joinColumns = @JoinColumn(name = "foodId"),
        inverseJoinColumns = @JoinColumn(name = "billId")
    )
    private List<Bill> bills;

}
