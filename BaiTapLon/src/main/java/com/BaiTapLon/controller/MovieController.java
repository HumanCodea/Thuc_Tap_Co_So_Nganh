package com.BaiTapLon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.model.Movie;
import com.BaiTapLon.service.MovieService;

@Controller
@RequestMapping(path = "")
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @GetMapping("/manageMoive")
    public String ManageMovie(Model model){

        List<Movie> list = movieService.getAllMovie();

        model.addAttribute("Movie", list);

        return "ManageMovie";
    }

    @GetMapping("/registerMovie")
    public String RegisterMovie(){
        return "RegisterMovie";
    }

}
