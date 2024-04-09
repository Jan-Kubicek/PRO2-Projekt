package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.service.PasazerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PasazerService pasazerService;

    @Autowired
    public CustomUserDetailsService(PasazerService pasazerService) {
        this.pasazerService = pasazerService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pasazer pasazer = pasazerService.findByEmail(email);
        if (pasazer == null) {
            throw new UsernameNotFoundException("Uživatel s e-mailem " + email + " nebyl nalezen.");
        }
        // Vytvoření objektu UserDetails na základě informací o uživateli z databáze
        return new CustomUserDetails(pasazer);
    }
}
