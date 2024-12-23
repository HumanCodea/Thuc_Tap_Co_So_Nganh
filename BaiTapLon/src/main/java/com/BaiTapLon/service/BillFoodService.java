package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.Bill_Food;
import com.BaiTapLon.repository.BillFoodRepository;

@Service
public class BillFoodService {
    
    @Autowired
    private BillFoodRepository billFoodRepository;


    public List<Bill_Food> getAllBill_Foods(int billId){
        return billFoodRepository.findBillFoodsByBillId(billId);
    }

    public List<Integer> getFoodId(int billId){
        return billFoodRepository.findFoodIdByBillId(billId);
    }

    public int getQualityFood(int billId){
        return billFoodRepository.findQualityFoodByBillId(billId);
    }

    public void saveBillFood(Bill_Food bill_Food){
        billFoodRepository.save(bill_Food);
    }

}
