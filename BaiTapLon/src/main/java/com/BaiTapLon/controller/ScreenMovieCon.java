package com.BaiTapLon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.model.ScreeningMovie;
import com.BaiTapLon.service.ScreenMovieService;

@Controller
@RequestMapping(path = "")
public class ScreenMovieCon {

    @Autowired
    private ScreenMovieService screenMovieService;
    
    @GetMapping("/manageScreeningMovie")
        public String ManageScreeningMovie(Model model){

            List<ScreeningMovie> screeningMovies = screenMovieService.getAllScreeningMovies();

            model.addAttribute("Screening", screeningMovies);

            return "ScreeningMovie";
        }

    @GetMapping("/deleteScreen/{id}")
    public String DeleteScreenMovie(@ModelAttribute("id") int id){

        screenMovieService.deleteMovie(id);

        return "redirect:/manageScreeningMovie";
    }

    @GetMapping("/registerScreen")
    public String RegisterScreenMovie(){




        return "RegisterScreen";
    }

    @GetMapping("/editScreen/{id}")
    public String EditScreenMovie(){
        


        return "EditScreen";
    }

}
