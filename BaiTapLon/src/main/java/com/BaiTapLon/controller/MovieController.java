package com.BaiTapLon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BaiTapLon.dto.MovieDTO;
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
    public String RegisterMovie(Model model){

        MovieDTO movieDto = new MovieDTO();

        model.addAttribute("movieDTO", movieDto);

        return "RegisterMovie";
    }

    @PostMapping("/registerMovie")
    public String SaveRegisterMovie(@ModelAttribute MovieDTO movieDTO) throws IOException{

        MultipartFile file = movieDTO.getPosterFile();

        upLoadFile(file);

        Movie movie = new Movie();
        movie.setNameMovie(movieDTO.getNameMovie());
        movie.setTypeMovie(movieDTO.getTypeMovie());
        movie.setAuthorMovie(movieDTO.getAuthorMovie());
        movie.setMovieCast(movieDTO.getMovieCast());
        movie.setTimeMovie(movieDTO.getTimeMovie());
        movie.setMovieDetail(movieDTO.getMovieDetail());
        movie.setReleaseMovie(movieDTO.getReleaseMovie());
        movie.setStatus("DC");
        movie.setLinkTrailer(movieDTO.getLinkTrailer());
        movie.setPosterMovie(file.getOriginalFilename());

        movieService.saveMovie(movie);

        return "redirect:/manageMoive";
    }

    @GetMapping("/editMovie/{id}")
    public String EditMovie(@ModelAttribute("id") int id,Model model){

        Movie movie = movieService.getMovieById(id);

       model.addAttribute("movie", movie);

        return "EditMovie";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(@ModelAttribute("movie") Movie movie,@RequestParam("posterFile") MultipartFile posterMovie){

        if(!posterMovie.isEmpty()){
            upLoadFile(posterMovie);
            movie.setPosterMovie(posterMovie.getOriginalFilename());
        }

        movie.setStatus("SC");
        movieService.saveMovie(movie);

        return "redirect:/manageMoive";
    }

    @GetMapping("/deleteMovie/{id}")
    public String DeleteMovie(@ModelAttribute("id") int id){

        movieService.deteleMovie(id);

        return "redirect:/manageMoive";
    }

    public void upLoadFile(MultipartFile file){

        String fileName = file.getOriginalFilename();

         try{
            String uploadDir = "BaiTapLon/src/main/resources/static/image/";
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath();

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }

    }

}
