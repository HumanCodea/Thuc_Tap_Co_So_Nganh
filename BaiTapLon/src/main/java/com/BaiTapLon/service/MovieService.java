package com.BaiTapLon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLon.model.Movie;
import com.BaiTapLon.repository.MovieRepository;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovie(){
        return movieRepository.findAll();
    }

    public void saveMovie(Movie movie){
        movieRepository.save(movie);
    }

    public void deteleMovie(int id){
        movieRepository.deleteById(id);
    }

    public Movie getMovieById(int id){
        return movieRepository.findById(id).get();
    }

}
