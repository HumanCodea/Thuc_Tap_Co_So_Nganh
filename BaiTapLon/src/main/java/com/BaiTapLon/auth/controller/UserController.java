package com.BaiTapLon.auth.controller;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.BaiTapLon.auth.entities.Custormer;
import com.BaiTapLon.auth.entities.OtpCode;
import com.BaiTapLon.auth.service.CustormerService;
import com.BaiTapLon.auth.service.OtpCodeService;
import com.BaiTapLon.dto.MailBody;
import com.BaiTapLon.service.EmailService;

@Controller
@RequestMapping(path = "")
public class UserController {

    @Autowired
    private CustormerService custormerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpCodeService otpCodeService;
    
    @GetMapping("/login")
    public String LoginScreen(){
        return "Login";
    }
   
    @GetMapping("/register")
    public String Register(){
        return "Register";
    }

    @PostMapping("/saveUser")
    public String SaveUser(@ModelAttribute Custormer custormer, RedirectAttributes redirectAttributes){
        custormerService.saveCustormer(custormer);
        // Khi sử dụng RedirectAttributes.addFlashAttribute, dữ liệu sẽ chỉ tồn tại trong request tiếp theo và không hiển thị trong URL
        redirectAttributes.addFlashAttribute("successfully", true);
        return "redirect:/login";
    }

    @GetMapping("/forgetPassword")
    public String ForgotPassword(){

        return "ForgetPassword";
    }

    @PostMapping("/verifyEmail")
    public String VerifyEmail(@ModelAttribute("email") String email, RedirectAttributes redirectAttributes){

        Custormer custormer = custormerService.findCustormerByEmail(email);

        if(custormer == null){
            return "redirect:/forgetPassword?emailNotFound";
        }

        Integer otp = generateOtp();

        MailBody mailBody = MailBody.builder()
            .to(email)
            .text("This is the Otp for your Forgot password request: " + otp)
            .subject("Otp for your Forgot password request")
            .build();

        OtpCode otpCode = new OtpCode();
        otpCode.setOtp(otp);
        otpCode.setExpirationTime(new Date(System.currentTimeMillis() + 70 * 1000));
        otpCode.setCustormer(custormer);

        emailService.sendSimpleMessage(mailBody);
        otpCodeService.saveOtpCode(otpCode);

        redirectAttributes.addFlashAttribute(email, true);

        return "ForgetPasswordOtp";
    }

    @GetMapping("/forgetPasswordOtp")
    public String ForgetPasswordOtp(@ModelAttribute("email") String email,Model model){

        model.addAttribute("email", email);

        return "ForgetPasswordOtp";
    }

    @PostMapping("/verifyOtp")
    public String VerifyOtp(@RequestParam("email") String email, @RequestParam("otp") int otp, Model model){

        Custormer custormer = custormerService.findCustormerByEmail(email);

        OtpCode otpCode = otpCodeService.findByOtpAndCustormer(otp, custormer);
            
        if(otpCode == null){
            return "redirect:/forgetPasswordOtp?otpNotFound";
        }

        //So sánh xem thời gian hết hạn có trước thời gian hiện tại không. Nếu có, điều này có nghĩa là OTP đã hết hạn.
        if(otpCode.getExpirationTime().before(Date.from(Instant.now()))){
            otpCodeService.deleteById(otpCode.getOtpId());
            return "redirect:/forgetPasswordOtp?otpExpired";
        }

        model.addAttribute("email", email);

        return "ChangePassword";
    }

    @GetMapping("/changePassword")
    public String ChangePassword(){



        return "ChangePassword";
    }

    @PostMapping("/changePassword")
    public String ChangePassword(@ModelAttribute("email") String email
                                ,@ModelAttribute("password") String password
                                ,@ModelAttribute("repeatPassword") String repeatPassword
                                ,RedirectAttributes redirectAttributes){

        if(!Objects.equals(password,repeatPassword)){
            return "redirect:/changePassword?differentPassword";
        }

        int custormerId = custormerService.findCustormerIdByUsername(email);
        Custormer custormer = custormerService.findCustormerById(custormerId);
        custormer.setPassword(password);
        
        custormerService.saveCustormer(custormer);

        redirectAttributes.addFlashAttribute("changePasswordSucces", true);

        return "redirect:/login";
    }

    // generate otp
    public Integer generateOtp(){
        Random random = new Random();

        return random.nextInt(100_000,999_999);
    }

}
