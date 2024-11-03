package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.ScreeningMovie;
import com.BaiTapLon.repository.ScreenMovieRepository;

@Service
public class ScreenMovieService {

    @Autowired
    private ScreenMovieRepository screenMovieRepository;
    
    public List<ScreeningMovie> getAllScreeningMovies(){
        return screenMovieRepository.findAll();
    }

    public void saveScreenMovie(ScreeningMovie screeningMovie){
        screenMovieRepository.save(screeningMovie);
    }

    public void deleteMovie(int id){
        screenMovieRepository.deleteById(id);
    }

    public ScreeningMovie findScreeningMovieById(int id){
        return screenMovieRepository.findById(id).get();
    }

}
