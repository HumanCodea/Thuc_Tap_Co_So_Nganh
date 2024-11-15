package com.BaiTapLon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.Seats;

@Repository
public interface SeatRepository extends JpaRepository<Seats, Integer>{
    
}
