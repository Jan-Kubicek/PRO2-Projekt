package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.repository.DispecerRepository;
import org.example.pro2projekt.repository.PasazerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private DispecerService dispecerRepository;

    @Autowired
    private PasazerService pasazerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Dispecer dispecer = dispecerRepository.findByEmail(username);
        Pasazer pasazer = pasazerRepository.findByEmail(username);

        if (dispecer != null) {
            return buildUserDetails(dispecer, "DISPECER");
        } else if (pasazer != null) {
            return buildUserDetails(pasazer, "PASAZER");
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private UserDetails buildUserDetails(Dispecer dispecer, String role) {
        //List<GrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority(role));
        return dispecer;
    }

    private UserDetails buildUserDetails(Pasazer pasazer, String role) {
        //List<GrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority(role));
        return pasazer;
    }
}