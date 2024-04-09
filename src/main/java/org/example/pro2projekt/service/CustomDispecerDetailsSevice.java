package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Dispecer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDispecerDetailsSevice implements UserDetailsService {
    private final DispecerService dispecerService;

    @Autowired
    public CustomDispecerDetailsSevice(DispecerService dispecerService) {
        this.dispecerService = dispecerService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Dispecer dispecer = dispecerService.findByEmail(email);
        if (dispecer == null) {
            throw new UsernameNotFoundException("Dispecer s emailem " + email + " nebyl nalezen.");
        }
        return new CustomUserDetails(dispecer);
    }
}
