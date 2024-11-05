package com.BaiTapLon.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.auth.service.CustormerService;
import com.BaiTapLon.model.Movie;
import com.BaiTapLon.model.Promotion;
import com.BaiTapLon.service.MovieService;
import com.BaiTapLon.service.PromotionService;

@Controller
@RequestMapping(path = "")
public class HomeController {

    @Autowired
    private CustormerService custormerService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private MovieService movieService;
    
    @GetMapping("/home")
    public String Home(Model model){

        List<Promotion> promotions = promotionService.getAllPromotion();

        model.addAttribute("Promotion", promotions);

        return "Home";
    }

    @GetMapping("/homeUser")
    public String HomeUser(Model model){

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        model.addAttribute("name", username);

        String role = getRole();

        if (role.equals("ADMIN")){
            return "HomeAdmin";
        }

        List<Promotion> promotions = promotionService.getAllPromotion();

        model.addAttribute("Promotion", promotions);

        return "HomeUser";
    }

    @GetMapping("/movieDetailUser")
    public String MoiveDetailUser(Model model){

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        model.addAttribute("name", username);
        
        return "MoiveDetailUser";
    }

    @GetMapping("/phimDangChieu")
    public String ListMovieDC(Model model){

        List<Movie> movies = movieService.getAllMovie();

        List<Movie> newMovie = new ArrayList<>();

        for(Movie m : movies){
            if(m.getStatus().equals("DC")){
                newMovie.add(m);
            }
        }

        model.addAttribute("Movie", newMovie);

        return "ListMovieDC";
    }

    @GetMapping("/phimSapChieu")
    public String ListMovieSC(Model model){

        List<Movie> movies = movieService.getAllMovie();

        List<Movie> newMovie = new ArrayList<>();

        for(Movie m : movies){
            if(m.getStatus().equals("SC")){
                newMovie.add(m);
            }
        }

        model.addAttribute("Movie", newMovie);

        return "ListMovieSC";
    }

    @GetMapping("/khuyenMai")
    public String ListPromotion(Model model){

        List<Promotion> promotions = promotionService.getAllPromotion();

        model.addAttribute("Promotion", promotions);

        return "ListPromotion";
    }

    @GetMapping("/movieDetail")
    public String MovieDetail(){
        return "MoiveDetail";
    }

    public String getUsername(){
        String email = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            email = userDetails.getUsername();

        }

        return email;

    }

    public String getRole(){

        Collection<? extends GrantedAuthority> authorities = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();


            authorities = userDetails.getAuthorities();
        }

        String role = null;

        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }

        return role;

    }

}
