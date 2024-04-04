package org.example.pro2projekt.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Dispecer {
    @Id
    @GeneratedValue
    private int DispecerID;
    private String Email;
    private String Heslo;
    private String Jmeno;
    private String Prijmeni;
    private String Rodne_cislo;
    private String Telefoni_cislo;

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
}
