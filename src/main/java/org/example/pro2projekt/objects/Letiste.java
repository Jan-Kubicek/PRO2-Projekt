package org.example.pro2projekt.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Letiste {
    @Id
    @GeneratedValue
    private int LetisteID;
    private int Kapacita;
    private String Mesto;
    private String Nazev;
    private String Stat;

    public Letiste() {
    }

    public Letiste( int kapacita, String mesto, String nazev, String stat) {
        Kapacita = kapacita;
        Mesto = mesto;
        Nazev = nazev;
        Stat = stat;
    }

    public int getLetisteID() {
        return LetisteID;
    }

    public void setLetisteID(int letisteID) {
        LetisteID = letisteID;
    }

    public int getKapacita() {
        return Kapacita;
    }

    public void setKapacita(int kapacita) {
        Kapacita = kapacita;
    }

    public String getMesto() {
        return Mesto;
    }

    public void setMesto(String mesto) {
        Mesto = mesto;
    }

    public String getNazev() {
        return Nazev;
    }

    public void setNazev(String nazev) {
        Nazev = nazev;
    }

    public String getStat() {
        return Stat;
    }

    public void setStat(String stat) {
        Stat = stat;
    }
}
