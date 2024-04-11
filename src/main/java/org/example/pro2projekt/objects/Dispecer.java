package org.example.pro2projekt.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Table(name = "Dispecer")
@Entity(name = "Dispecer")
@Immutable
public class Dispecer implements UserDetails {
    @Id
    @GeneratedValue
    private int DispecerID;
    private String Email;
    private String Heslo;
    private String Jmeno;
    private String Prijmeni;
    private String Rodne_cislo;
    private String Telefoni_cislo;
    private String role = "ROLE_DISPECER";

    public Dispecer() {
    }

    public Dispecer(String email, String heslo, String jmeno, String prijmeni, String rodne_cislo, String telefoni_cislo) {
        Email = email;
        Heslo = heslo;
        Jmeno = jmeno;
        Prijmeni = prijmeni;
        Rodne_cislo = rodne_cislo;
        Telefoni_cislo = telefoni_cislo;
    }
    public String getRole() {
        return role;
    }

    public int getDispecerID() {
        return DispecerID;
    }

    public void setDispecerID(int dispecerID) {
        DispecerID = dispecerID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHeslo() {
        return Heslo;
    }

    public void setHeslo(String heslo) {
        Heslo = heslo;
    }

    public String getJmeno() {
        return Jmeno;
    }

    public void setJmeno(String jmeno) {
        Jmeno = jmeno;
    }

    public String getPrijmeni() {
        return Prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        Prijmeni = prijmeni;
    }

    public String getRodne_cislo() {
        return Rodne_cislo;
    }

    public void setRodne_cislo(String rodne_cislo) {
        Rodne_cislo = rodne_cislo;
    }

    public String getTelefoni_cislo() {
        return Telefoni_cislo;
    }

    public void setTelefoni_cislo(String telefoni_cislo) {
        Telefoni_cislo = telefoni_cislo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_DISPECER"));
    }

    @Override
    public String getPassword() {
        return Heslo;
    }

    @Override
    public String getUsername() {
        return Email;
    }

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
    public String getRoles(){ return role;
    }
}
