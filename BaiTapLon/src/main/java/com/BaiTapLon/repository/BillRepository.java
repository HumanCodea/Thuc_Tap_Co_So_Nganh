package com.BaiTapLon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{
    
    @Query("Select b From Bill b Where b.custormer.custormerId = :custormerId")
    public List<Bill> findBillsByCustomerId(int custormerId);

}
