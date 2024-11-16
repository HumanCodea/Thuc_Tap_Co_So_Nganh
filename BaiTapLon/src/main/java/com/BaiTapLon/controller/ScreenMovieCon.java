package com.BaiTapLon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLon.dto.ScreenDTO;
import com.BaiTapLon.model.Movie;
import com.BaiTapLon.model.ScreeningMovie;
import com.BaiTapLon.model.ScreeningRoom;
import com.BaiTapLon.service.MovieService;
import com.BaiTapLon.service.RoomService;
import com.BaiTapLon.service.ScreenMovieService;

@Controller
@RequestMapping(path = "")
public class ScreenMovieCon {

    @Autowired
    private ScreenMovieService screenMovieService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RoomService roomService;
    
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
    public String RegisterScreenMovie(Model model){

        ScreenDTO screenDTO = new ScreenDTO();

        model.addAttribute("screenDTO", screenDTO);


        return "RegisterScreen";
    }

    @PostMapping("/registerScreen")
    public String SaveRegisterScreenMovie(@ModelAttribute ScreenDTO screenDTO){

        ScreeningMovie screeningMovie = new ScreeningMovie();

        Movie movie = movieService.getMovieById(screenDTO.getMovieId());
        ScreeningRoom screeningRoom = roomService.findRoomById(screenDTO.getRoomId());

        screeningMovie.setDates(screenDTO.getDates());
        screeningMovie.setHour(screenDTO.getHour());
        screeningMovie.setMovie(movie);
        screeningMovie.setScreeningRoom(screeningRoom);

        screenMovieService.saveScreenMovie(screeningMovie);

        return "redirect:/manageScreeningMovie";
    }

    @GetMapping("/editScreen/{id}")
    public String EditScreenMovie(@ModelAttribute("id") int id,Model model){

        ScreeningMovie screeningMovie = screenMovieService.findScreeningMovieById(id);

        ScreenDTO screenDTO = new ScreenDTO();

        screenDTO.setSreeningId(screeningMovie.getSreeningId());
        screenDTO.setDates(screeningMovie.getDates());
        screenDTO.setHour(screeningMovie.getHour());
        screenDTO.setMovieId(screeningMovie.getMovie().getMovieId());
        screenDTO.setRoomId(screeningMovie.getScreeningRoom().getRoomId());

        model.addAttribute("screen", screenDTO);

        return "EditScreen";
    }

    @PostMapping("/saveScreen")
    public String saveEditScreen(@ModelAttribute ScreenDTO screenDTO){

        Movie newMovie = movieService.getMovieById(screenDTO.getMovieId());
        ScreeningRoom newScreeningRoom = roomService.findRoomById(screenDTO.getRoomId());

        ScreeningMovie screeningMovie = new ScreeningMovie();

        screeningMovie.setSreeningId(screenDTO.getSreeningId());
        screeningMovie.setDates(screenDTO.getDates());
        screeningMovie.setHour(screenDTO.getHour());
        screeningMovie.setMovie(newMovie);
        screeningMovie.setScreeningRoom(newScreeningRoom);

        screenMovieService.saveScreenMovie(screeningMovie);

        return "redirect:/manageScreeningMovie";
    }

}
