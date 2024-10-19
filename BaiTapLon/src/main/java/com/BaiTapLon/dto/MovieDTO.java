package com.BaiTapLon.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MovieDTO {
    
    private int movieId;

    private String nameMovie;

    private String typeMovie;

    private String authorMovie;

    private String movieCast;

    private String timeMovie;

    private String posterMovie;

    private MultipartFile releaseMovie;

}
