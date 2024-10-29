package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.ScreeningRoom;
import com.BaiTapLon.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
 
    public List<ScreeningRoom> getAllRooms(){
        return roomRepository.findAll();
    }

    public ScreeningRoom findRoomById(int id){
        return roomRepository.findById(id).get();
    }

    public void saveRoom(ScreeningRoom room){
        roomRepository.save(room);
    }

    public void deleteRoom(int id){
        roomRepository.deleteById(id);
    }
    
}
