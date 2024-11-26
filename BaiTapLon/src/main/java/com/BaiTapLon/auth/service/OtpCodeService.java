package com.BaiTapLon.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.auth.entities.Custormer;
import com.BaiTapLon.auth.entities.OtpCode;
import com.BaiTapLon.auth.repository.OtpCodeRepository;

@Service
public class OtpCodeService {

    @Autowired
    private OtpCodeRepository otpCodeRepository;

    public OtpCode findByOtpAndCustormer(Integer otp, Custormer custormer){
        return otpCodeRepository.findByOtpAndCustormer(otp, custormer);
    }

    public void saveOtpCode(OtpCode otpCode){
        otpCodeRepository.save(otpCode);
    }

    public void deleteById(int id){
        otpCodeRepository.deleteById(id);
    }

}
