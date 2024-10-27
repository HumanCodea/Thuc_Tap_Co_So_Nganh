package com.BaiTapLon.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.BaiTapLon.dto.MovieDTO;
import com.BaiTapLon.model.Movie;
import com.BaiTapLon.service.MovieService;

@Controller
@RequestMapping(path = "")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Value("${project.poster}")
    private String path;
    
    @GetMapping("/manageMoive")
    public String ManageMovie(Model model){

        List<Movie> list = movieService.getAllMovie();

        model.addAttribute("Movie", list);

        return "ManageMovie";
    }

    @GetMapping("/registerMovie")
    public String RegisterMovie(Model model){

        MovieDTO movieDto = new MovieDTO();

        model.addAttribute("movieDTO", movieDto);

        return "RegisterMovie";
    }

    @PostMapping("/registerMovie")
    public String SaveRegisterMovie(@ModelAttribute MovieDTO movieDTO) throws IOException{

        MultipartFile file = movieDTO.getPosterMovie();

        String fileName = file.getOriginalFilename();
        
        String filePath = path + File.separator + fileName;

        File f = new File(path);

        if (!f.exists()) {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        Movie movie = new Movie();
        movie.setNameMovie(movieDTO.getNameMovie());
        movie.setTypeMovie(movieDTO.getTypeMovie());
        movie.setAuthorMovie(movieDTO.getAuthorMovie());
        movie.setMovieCast(movieDTO.getMovieCast());
        movie.setTimeMovie(movieDTO.getTimeMovie());
        movie.setMovieDetail(movieDTO.getMovieDetail());
        movie.setReleaseMovie(movieDTO.getReleaseMovie());
        movie.setStatus("DC");
        movie.setPosterMovie(fileName);

        movieService.saveMovie(movie);

        return "redirect:/manageMoive";
    }

}
