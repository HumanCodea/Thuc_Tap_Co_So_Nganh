package com.BaiTapLon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.ScreeningMovie;

@Repository
public interface ScreenMovieRepository extends JpaRepository<ScreeningMovie, Integer>{
    
}
