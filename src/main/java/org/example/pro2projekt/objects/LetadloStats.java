package org.example.pro2projekt.objects;

public class LetadloStats {
    private String vyrobce;
    private String nazev;
    private int pocet;
    private int allPlanes;

    public LetadloStats() {
    }

    public String getVyrobce() {
        return vyrobce;
    }

    public void setVyrobce(String vyrobce) {
        this.vyrobce = vyrobce;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public int getAllPlanes() {
        return allPlanes;
    }

    public void setAllPlanes(int allPlanes) {
        this.allPlanes = allPlanes;
    }
}
