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

    public List<Movie>  getAllMovie(){
        return movieRepository.findAll();
    }

}