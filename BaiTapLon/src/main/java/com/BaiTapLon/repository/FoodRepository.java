package com.BaiTapLon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,Integer> {
    
    @Query("Select priceFood From Food Where foodId = :foodId")
    public int findValueByFoodId(int foodId);

}
