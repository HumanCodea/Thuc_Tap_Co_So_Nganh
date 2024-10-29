package com.BaiTapLon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.dto.RoomDTO;
import com.BaiTapLon.model.ScreeningRoom;
import com.BaiTapLon.service.RoomService;

@Controller
@RequestMapping(path = "")
public class ScreeningRoomCon {

    @Autowired
    private RoomService roomService;
    
    @GetMapping("/manageScreeningRoom")
    public String ManageScreeningRoom(Model model){

        List<ScreeningRoom> rooms = roomService.getAllRooms();

        model.addAttribute("Room", rooms);


        return "ScreeningRoom";
    }

    @GetMapping("/registerRoom")
    public String RegisterRoom(Model model){
        RoomDTO roomDTO = new RoomDTO();
        model.addAttribute("roomDTO", roomDTO);
        return "RegisterRoom";
    }

    @PostMapping("/registerRoom")
    public String SaveRegisterRoom(@ModelAttribute RoomDTO roomDTO){

        ScreeningRoom screeningRoom = new ScreeningRoom();

        screeningRoom.setRoomName(roomDTO.getRoomName());
        screeningRoom.setQuantityChair(roomDTO.getQuantityChair());
        screeningRoom.setDetailRoom(roomDTO.getDetailRoom());

        roomService.saveRoom(screeningRoom);

        return "redirect:/manageScreeningRoom";
    }

    @GetMapping("/editRoom/{id}")
    public String EditRoom(@ModelAttribute("id") int id, Model model){

        ScreeningRoom screeningRoom = roomService.findRoomById(id);

        model.addAttribute("room", screeningRoom);

        return "EditRoom";
    }

    @PostMapping("/saveRoom")
    public String SaveEditRoom(@ModelAttribute ScreeningRoom screeningRoom){

        roomService.saveRoom(screeningRoom);

        return "redirect:/manageScreeningRoom";
    }

    @GetMapping("/deleteRoom/{id}")
    public String DeleteRoom(@ModelAttribute("id") int id){

        roomService.deleteRoom(id);

        return "redirect:/manageScreeningRoom";
    }

}
