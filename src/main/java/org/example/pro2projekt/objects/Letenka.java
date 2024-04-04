package org.example.pro2projekt.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Letenka {
    @Id
    @GeneratedValue
    private int LetenkaID;
    private int LetID;
    private int PasazerID;
    private int jeSkupinova;
    private int Pocet_pasazeru;
    private String Trida;

    public Letenka() {
    }

    public Letenka(int letID, int pasazerID, int jeSkupinova, int pocet_pasazeru, String trida) {
        LetID = letID;
        PasazerID = pasazerID;
        this.jeSkupinova = jeSkupinova;
        Pocet_pasazeru = pocet_pasazeru;
        Trida = trida;
    }

    public int getLetenkaID() {
        return LetenkaID;
    }

    public void setLetenkaID(int letenkaID) {
        LetenkaID = letenkaID;
    }

    public int getLetID() {
        return LetID;
    }

    public void setLetID(int letID) {
        LetID = letID;
    }

    public int getPasazerID() {
        return PasazerID;
    }

    public void setPasazerID(int pasazerID) {
        PasazerID = pasazerID;
    }

    public int getJeSkupinova() {
        return jeSkupinova;
    }

    public void setJeSkupinova(int jeSkupinova) {
        this.jeSkupinova = jeSkupinova;
    }

    public int getPocet_pasazeru() {
        return Pocet_pasazeru;
    }

    public void setPocet_pasazeru(int pocet_pasazeru) {
        Pocet_pasazeru = pocet_pasazeru;
    }

    public String getTrida() {
        return Trida;
    }

    public void setTrida(String trida) {
        Trida = trida;
    }
}
