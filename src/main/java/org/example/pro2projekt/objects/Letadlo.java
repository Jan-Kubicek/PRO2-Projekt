package org.example.pro2projekt.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Letadlo {
    @Id
    @GeneratedValue
    private int LetadloID;
    private int SpolecnostID;
    private String Nazev;
    private String Rok_vyroby;
    private String Stav;
    private String Typ;
    private String Vyrobce;

    public Letadlo() {
    }

    public Letadlo(int spolecnostID, String nazev, String rok_vyroby, String stav, String typ, String vyrobce) {
        SpolecnostID = spolecnostID;
        Nazev = nazev;
        Rok_vyroby = rok_vyroby;
        Stav = stav;
        Typ = typ;
        Vyrobce = vyrobce;
    }

    public int getLetadloID() {
        return LetadloID;
    }

    public void setLetadloID(int letadloID) {
        LetadloID = letadloID;
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

    public String getRok_vyroby() {
        return Rok_vyroby;
    }

    public void setRok_vyroby(String rok_vyroby) {
        Rok_vyroby = rok_vyroby;
    }

    public String getStav() {
        return Stav;
    }

    public void setStav(String stav) {
        Stav = stav;
    }

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String typ) {
        Typ = typ;
    }

    public String getVyrobce() {
        return Vyrobce;
    }

    public void setVyrobce(String vyrobce) {
        Vyrobce = vyrobce;
    }
}
