package org.example.pro2projekt.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Zavazadlo {
    @Id
    @GeneratedValue
    private int ZavazadloID;
    private int PasazerID;
    private int Typ_zavazadlaID;
    private int Krehke;
    private int Sirka;
    private int Vyska;
    private int Vaha;

    public Zavazadlo() {
    }

    public Zavazadlo(int pasazerID, int typ_zavazadlaID, int krehke, int sirka, int vyska, int vaha) {
        PasazerID = pasazerID;
        Typ_zavazadlaID = typ_zavazadlaID;
        Krehke = krehke;
        Sirka = sirka;
        Vyska = vyska;
        Vaha = vaha;
    }

    public int getZavazadloID() {
        return ZavazadloID;
    }

    public void setZavazadloID(int zavazadloID) {
        ZavazadloID = zavazadloID;
    }

    public int getPasazerID() {
        return PasazerID;
    }

    public void setPasazerID(int pasazerID) {
        PasazerID = pasazerID;
    }

    public int getTyp_zavazadlaID() {
        return Typ_zavazadlaID;
    }

    public void setTyp_zavazadlaID(int typ_zavazadlaID) {
        Typ_zavazadlaID = typ_zavazadlaID;
    }

    public int getKrehke() {
        return Krehke;
    }

    public void setKrehke(int krehke) {
        Krehke = krehke;
    }

    public int getSirka() {
        return Sirka;
    }

    public void setSirka(int sirka) {
        Sirka = sirka;
    }

    public int getVyska() {
        return Vyska;
    }

    public void setVyska(int vyska) {
        Vyska = vyska;
    }

    public int getVaha() {
        return Vaha;
    }

    public void setVaha(int vaha) {
        Vaha = vaha;
    }
}
