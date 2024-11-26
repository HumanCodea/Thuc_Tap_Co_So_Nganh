package com.BaiTapLon.auth.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OtpCode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer otpId;

    @Column(nullable = false)
    private Integer otp;

    @Column(nullable = false)
    private Date expirationTime;

    @ManyToOne
    private Custormer custormer;

}
