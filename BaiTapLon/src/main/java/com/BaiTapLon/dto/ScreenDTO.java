package com.BaiTapLon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScreenDTO {
    
    private int sreeningId;

    private String dates;

    private String hour;

    private int movieId;

    private int roomId;

}
