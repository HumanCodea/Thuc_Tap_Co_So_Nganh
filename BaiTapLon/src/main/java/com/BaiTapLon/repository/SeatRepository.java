package com.BaiTapLon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.Seats;

@Repository
public interface SeatRepository extends JpaRepository<Seats, Integer>{
    
    @Query("Select chairName From Seats Where seatId = :seatId")
    public String findSeatNameBySeatId(int seatId);

}
