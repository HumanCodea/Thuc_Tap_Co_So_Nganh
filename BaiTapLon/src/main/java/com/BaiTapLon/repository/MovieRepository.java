package com.BaiTapLon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

    @Query("Select m from Movie m Where m.nameMovie like %?1%")
    public List<Movie> findByMovieName(String nameMovie);

}
