package com.BaiTapLon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.ScreeningMovie;

@Repository
public interface ScreenMovieRepository extends JpaRepository<ScreeningMovie, Integer>{
    
    @Query("Select s From ScreeningMovie s Where s.movie.movieId = :movieId")
    public List<ScreeningMovie> findScreeningMoviesByMovieId(int movieId);

}
