package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Pasazer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PasazerService pasazerRepository;

    @Autowired
    public UserDetailsServiceImpl(PasazerService pasazerRepository) {
        this.pasazerRepository = pasazerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pasazer pasazer = pasazerRepository.findByEmail(username);

        if (pasazer != null) {
            return buildUserDetails(pasazer, pasazer.getRole());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private UserDetails buildUserDetails(Pasazer pasazer, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return pasazer;
    }
}