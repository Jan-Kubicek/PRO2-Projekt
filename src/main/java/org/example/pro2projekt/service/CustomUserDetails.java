package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private Pasazer pasazer = null;
    private Dispecer dispecer = null;

    public CustomUserDetails(Pasazer pasazer, Dispecer dispecer) {
        this.pasazer = pasazer;
        this.dispecer = dispecer;
    }

    public CustomUserDetails(Pasazer pasazer) {
        this.pasazer = pasazer;
    }

    public CustomUserDetails(Dispecer dispecer) {
        this.dispecer = dispecer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Vracíme zde kolekci oprávnění uživatele, pokud je aplikováno
        // Pro jednoduchost můžeme vrátit prázdnou kolekci
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        String heslo = null;
        if(pasazer != null)
            heslo =  pasazer.getHeslo();
        if(dispecer != null)
            heslo = dispecer.getHeslo();
        return heslo;
    }

    @Override
    public String getUsername() {
        String email = null;
        if(pasazer != null)
            email =  pasazer.getEmail();
        if(dispecer != null)
            email = dispecer.getEmail();
        return email;
    }

    // Další metody pro získání informací o uživateli, jako například datum expirace účtu, účet je neuzamčený atd.
    // Tyto metody mohou být implementovány podle potřeby.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
