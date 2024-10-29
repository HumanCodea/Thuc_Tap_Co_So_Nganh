package com.BaiTapLon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.ScreeningRoom;

@Repository
public interface RoomRepository extends JpaRepository<ScreeningRoom,Integer>{
    
}
