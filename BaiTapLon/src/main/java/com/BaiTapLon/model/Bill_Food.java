package com.BaiTapLon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill_Food {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billFoodId;

    private int quantityFood;

    @ManyToOne
    private Food food;

    @ManyToOne
    private Bill bill;

}
