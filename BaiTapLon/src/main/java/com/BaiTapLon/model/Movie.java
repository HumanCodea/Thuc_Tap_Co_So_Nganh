package com.BaiTapLon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movie {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    @Column(nullable = false)
    @NotBlank(message = "This name movie field can not be blank")
    private String nameMovie;

    @Column(nullable = false)
    @NotBlank(message = "This type movie field can not be blank")
    private String typeMovie;

    @Column(nullable = false)
    @NotBlank(message = "This author movie field can not be blank")
    private String authorMovie;

    @Column(nullable = false)
    @NotBlank(message = "This movie cast field can not be blank")
    private String movieCast;

    @Column(nullable = false)
    @NotBlank(message = "This time movie field can not be blank")
    private String timeMovie;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's poster")
    private String posterMovie;
    
    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's release")
    private String releaseMovie;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's status")
    private String status;

}
