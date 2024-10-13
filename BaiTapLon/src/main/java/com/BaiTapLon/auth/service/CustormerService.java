package com.BaiTapLon.auth.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BaiTapLon.auth.entities.Custormer;
import com.BaiTapLon.auth.repository.CustormerRepository;

@Service
public class CustormerService implements UserDetailsService{

    @Autowired
    private CustormerRepository custormerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        List<Custormer> custormers =  custormerRepository.findByEmail(username);
        String password = null;
        List<GrantedAuthority> roles = null;

        if(custormers.isEmpty()){
            throw new UsernameNotFoundException("UserDetails have not find username = " + username);
        }

        username = custormers.get(0).getEmail();
        password = custormers.get(0).getPassword();
        roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(custormers.get(0).getRoles().name()));


        return new User(username, password, roles);
    }
    
}
