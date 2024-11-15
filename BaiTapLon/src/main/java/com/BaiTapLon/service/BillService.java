package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.Bill;
import com.BaiTapLon.repository.BillRepository;

@Service
public class BillService {
    
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills(int custormerId){
        return billRepository.findBillsByCustomerId(custormerId);
    }

    public void StoreBill(Bill bill){
        billRepository.save(bill);
    }

}
