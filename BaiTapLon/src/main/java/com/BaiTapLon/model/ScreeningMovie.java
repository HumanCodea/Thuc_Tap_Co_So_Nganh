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
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ScreeningMovie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sreeningId;

    @Column(nullable = false)
    @NotBlank(message = "This time screening field can not be blank")
    private String timeScreening;

}
