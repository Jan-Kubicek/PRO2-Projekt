package org.example.pro2projekt.objects;

public class PasazerStats {
    private String Typ;
    private int Pocet;
    private String Popis;
    private int AllPasazers;

    public PasazerStats() {

    }

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String typ) {
        Typ = typ;
    }

    public int getPocet() {
        return Pocet;
    }

    public void setPocet(int pocet) {
        Pocet = pocet;
    }

    public String getPopis() {
        return Popis;
    }

    public void setPopis(String popis) {
        Popis = popis;
    }

    public int getAllPasazers() {
        return AllPasazers;
    }

    public void setAllPasazers(int allPasazers) {
        AllPasazers = allPasazers;
    }
}
