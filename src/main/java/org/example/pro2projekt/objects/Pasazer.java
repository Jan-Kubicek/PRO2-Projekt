package org.example.pro2projekt.objects;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Table(name = "Pasazer")
@Entity(name = "Pasazer")
@Immutable
public class Pasazer implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name ="PasazerID")
    private int PasazerID;
    @Column(name= "Typ_pasazeraID")
    private int Typ_pasazeraID;
    @Column(name= "Datum_narozeni")
    private Date Datum_narozeni;
    @Column(name= "Email")
    private String Email;
    @Column(name= "Heslo")
    private String Heslo;
    @Column(name= "Jmeno")
    private String Jmeno;
    @Column(name= "Pohlavi")
    private int Pohlavi;
    @Column(name= "Prijmeni")
    private String Prijmeni;
    @Column(name= "Rodne_cislo")
    private String Rodne_cislo;
    @Column(name= "Telefoni_cislo")
    private String Telefoni_cislo;
    private String role ;
    public Pasazer(){

    }

    public Pasazer( int typ_pasazeraID, Date datum_narozeni, String email, String heslo, String jmeno, int pohlavi, String prijmeni, String rodne_cislo, String telefoni_cislo) {
        Typ_pasazeraID = typ_pasazeraID;
        Datum_narozeni = datum_narozeni;
        Email = email;
        Heslo = heslo;
        Jmeno = jmeno;
        Pohlavi = pohlavi;
        Prijmeni = prijmeni;
        Rodne_cislo = rodne_cislo;
        Telefoni_cislo = telefoni_cislo;
        if(typ_pasazeraID == 6){
            role =  "ROLE_DISPECER";
        }else {
            role = "ROLE_PASAZER";
        }
    }

    public Pasazer(int typ_pasazeraID, String email, String heslo, String jmeno, int pohlavi, String prijmeni, String rodne_cislo, String telefoni_cislo) {
        Typ_pasazeraID = typ_pasazeraID;
        Email = email;
        Heslo = heslo;
        Jmeno = jmeno;
        Pohlavi = pohlavi;
        Prijmeni = prijmeni;
        Rodne_cislo = rodne_cislo;
        Telefoni_cislo = telefoni_cislo;
        if(typ_pasazeraID == 6){
            role =  "ROLE_DISPECER";
        }else{
            role = "ROLE_PASAZER";
        }
    }

    public String getRole() {
        return role;
    }

    public int getPasazerID() {
        return PasazerID;
    }

    public void setPasazerID(int pasazerID) {
        PasazerID = pasazerID;
    }

    public int getTyp_pasazeraID() {
        return Typ_pasazeraID;
    }

    public void setTyp_pasazeraID(int typ_pasazeraID) {
        Typ_pasazeraID = typ_pasazeraID;
        if(typ_pasazeraID == 6){
            role =  "ROLE_DISPECER";
        }else{
            role = "ROLE_PASAZER";
        }
    }

    public Date getDatum_narozeni() {
        return Datum_narozeni;
    }

    public void setDatum_narozeni(Date datum_narozeni) {
        Datum_narozeni = datum_narozeni;
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

    public int getPohlavi() {
        return Pohlavi;
    }

    public void setPohlavi(int pohlavi) {
        Pohlavi = pohlavi;
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
        return Collections.singletonList(new SimpleGrantedAuthority(role));
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
