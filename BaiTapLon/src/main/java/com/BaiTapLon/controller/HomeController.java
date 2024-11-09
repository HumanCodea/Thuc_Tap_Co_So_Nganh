package com.BaiTapLon.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.auth.service.CustormerService;
import com.BaiTapLon.model.Food;
import com.BaiTapLon.model.Movie;
import com.BaiTapLon.model.Promotion;
import com.BaiTapLon.service.FoodService;
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

    @Autowired
    private FoodService foodService;
    
    @GetMapping("/home")
    public String Home(Model model){

        List<Promotion> promotions = promotionService.getAllPromotion();
        List<Movie> movies = movieService.getAllMovie();
        List<Movie> movieDC = new ArrayList<>();
        List<Movie> movieSC = new ArrayList<>();
        int a = 0,b=0;

        model.addAttribute("Promotion", promotions);

        for(Movie m : movies){
            if(m.getStatus().equals("DC")){
                movieDC.add(m);
                a++;
            }
            if(a == 4){
                break;
            }
        }

        for(Movie m : movies){
            if(m.getStatus().equals("SC")){
                movieSC.add(m);
                b++;
            }
            if(b == 4){
                break;
            }
        }

        model.addAttribute("MovieSC", movieSC);
        model.addAttribute("MovieDC", movieDC);

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

        List<Movie> movies = movieService.getAllMovie();
        List<Movie> movieDC = new ArrayList<>();
        List<Movie> movieSC = new ArrayList<>();
        int a = 0,b=0;

        for(Movie m : movies){
            if(m.getStatus().equals("DC")){
                movieDC.add(m);
                a++;
            }
            if(a == 4){
                break;
            }
        }

        for(Movie m : movies){
            if(m.getStatus().equals("SC")){
                movieSC.add(m);
                b++;
            }
            if(b == 4){
                break;
            }
        }

        model.addAttribute("MovieSC", movieSC);
        model.addAttribute("MovieDC", movieDC);

        List<Promotion> promotions = promotionService.getAllPromotion();

        model.addAttribute("Promotion", promotions);

        return "HomeUser";
    }

    @GetMapping("/phimDangChieu")
    public String ListMovieDC(Model model){

        List<Movie> movies = movieService.getAllMovie();

        List<Movie> newMovie = new ArrayList<>();

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        for(Movie m : movies){
            if(m.getStatus().equals("DC")){
                newMovie.add(m);
            }
        }

        model.addAttribute("name", username);

        model.addAttribute("Movie", newMovie);

        return "ListMovieDC";
    }

    @GetMapping("/phimSapChieu")
    public String ListMovieSC(Model model){

        List<Movie> movies = movieService.getAllMovie();

        List<Movie> newMovie = new ArrayList<>();

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        model.addAttribute("name", username);

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

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        model.addAttribute("name", username);

        model.addAttribute("Promotion", promotions);

        return "ListPromotion";
    }

    @GetMapping("/promotion_detail/{id}")
    public String PromotionDetail(@ModelAttribute("id") int id, Model model){
        
        Promotion promotion = promotionService.findPromotionById(id);

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        model.addAttribute("name", username);

        model.addAttribute("Promotion", promotion);

        return "PromotionDetail";
    }

    @GetMapping("/movieDetail/{id}")
    public String MovieDetail(@ModelAttribute("id") int id, Model model){

        Movie movie = movieService.getMovieById(id);

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        model.addAttribute("name", username);

        model.addAttribute("Movie", movie);

        return "MoiveDetail";
    }

    @GetMapping("/search_movie")
    public String SearchMovie(@Param("nameMovie") String nameMovie, Model model){

        List<Movie> movies = movieService.findByMovieName(nameMovie);

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        model.addAttribute("name", username);

        model.addAttribute("nameMovie", nameMovie);

        model.addAttribute("Movie", movies);

        return "SearchMovie";
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

    @GetMapping("/bookTicket/{id}")
    public String BookTicket(@ModelAttribute("id") int id, Model model){

        Movie movie = movieService.getMovieById(id);

        String email = getUsername();

        String username = custormerService.findByUsername(email);

        List<Food> foods = foodService.getAllFood();

        model.addAttribute("Food", foods);

        model.addAttribute("name", username);

        model.addAttribute("Movie", movie);

        return "BookTicket";
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
