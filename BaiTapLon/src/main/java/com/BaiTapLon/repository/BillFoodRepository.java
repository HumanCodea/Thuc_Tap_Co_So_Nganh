package com.BaiTapLon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.Bill_Food;

@Repository
public interface BillFoodRepository extends JpaRepository<Bill_Food, Integer>{

    @Query("Select bl From Bill_Food bl Where bl.bill.billId = :billId")
    public List<Bill_Food> findBillFoodsByBillId(int billId);

}
