package com.BaiTapLon.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BaiTapLon.auth.entities.Custormer;

@Repository
public interface CustormerRepository extends JpaRepository<Custormer, Integer>{
    
    public List<Custormer> findByEmail(String email);

    @Query("Select username From Custormer where email like ?1")
    public String findByUsername(String email);

}