package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetisteTest {

    Letiste letiste;

    private void setLetiste(){
        this.letiste = new Letiste(40,"Praha","Letiště V.H.","Česká republika");
    }

    @Test
    void setLetisteID() {
        setLetiste();
        Letiste letiste1 = this.letiste;
        letiste1.setLetisteID(10);
        assertEquals(10,letiste1.getLetisteID());
    }

    @Test
    void setKapacita() {
        setLetiste();
        Letiste letiste1 = this.letiste;
        letiste1.setKapacita(65);
        assertEquals(65,letiste1.getKapacita());
    }

    @Test
    void setMesto() {
        setLetiste();
        Letiste letiste1 = this.letiste;
        letiste1.setMesto("Brno");
        assertEquals("Brno",letiste1.getMesto());
    }

    @Test
    void setNazev() {
        setLetiste();
        Letiste letiste1 = this.letiste;
        letiste1.setNazev("Letiště M.R.Š");
        assertEquals("Letiště M.R.Š",letiste1.getNazev());
    }

    @Test
    void setStat() {
        setLetiste();
        Letiste letiste1 = this.letiste;
        letiste1.setStat("Polsko");
        assertEquals("Polsko",letiste1.getStat());
    }
}