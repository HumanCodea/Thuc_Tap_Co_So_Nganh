package com.BaiTapLon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    @Query("Select namePromotion From Promotion Where promotionId = :promotionId")
    public String findNamePromotionByPromotionId(int promotionId);

}
