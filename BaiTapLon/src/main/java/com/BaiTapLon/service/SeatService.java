package com.BaiTapLon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.Seats;
import com.BaiTapLon.repository.SeatRepository;

@Service
public class SeatService {
    
    @Autowired
    private SeatRepository seatRepository;

    public void saveSeat(Seats seats){
        seatRepository.save(seats);
    }

}
