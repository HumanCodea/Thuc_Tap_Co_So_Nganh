package com.BaiTapLon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomDTO {
    
    private int roomId;

    private String roomName;

    private int quantityChair;

    private String detailRoom;

}
