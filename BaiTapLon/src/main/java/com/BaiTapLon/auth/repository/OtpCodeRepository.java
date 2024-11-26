package com.BaiTapLon.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.auth.entities.Custormer;
import com.BaiTapLon.auth.entities.OtpCode;

@Repository
public interface OtpCodeRepository extends JpaRepository<OtpCode, Integer>{
    
    @Query("Select fp from OtpCode fp where fp.otp = ?1 and fp.custormer = ?2")
    public OtpCode findByOtpAndCustormer(Integer otp, Custormer custormer);

}
