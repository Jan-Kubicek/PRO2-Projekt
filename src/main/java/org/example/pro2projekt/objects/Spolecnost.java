package org.example.pro2projekt.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Spolecnost {
    @Id
    @GeneratedValue
    private int SpolecnostID;
    private String Nazev;
    private String Sidlo;

    public Spolecnost() {
    }

    public Spolecnost(String nazev, String sidlo) {
        Nazev = nazev;
        Sidlo = sidlo;
    }

    public int getSpolecnostID() {
        return SpolecnostID;
    }

    public void setSpolecnostID(int spolecnostID) {
        SpolecnostID = spolecnostID;
    }

    public String getNazev() {
        return Nazev;
    }

    public void setNazev(String nazev) {
        Nazev = nazev;
    }

    public String getSidlo() {
        return Sidlo;
    }

    public void setSidlo(String sidlo) {
        Sidlo = sidlo;
    }
}
