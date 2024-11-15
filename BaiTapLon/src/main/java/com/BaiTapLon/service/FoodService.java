package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.Food;
import com.BaiTapLon.repository.FoodRepository;

@Service
public class FoodService {
    
    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAllFood(){
        return foodRepository.findAll();
    }

    public Food findFoodById(int id){
        return foodRepository.findById(id).get();
    }

    public void saveFood(Food food){
        foodRepository.save(food);
    }

    public void deleteFood(int id){
        foodRepository.deleteById(id);
    }

    public int getPriceFood(int foodId){
        return foodRepository.findValueByFoodId(foodId);
    }

}
