package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetadloTest {

    Letadlo letadlo;
    private void setLetadlo(){
        letadlo = new Letadlo(2,"Airbus A320","2003","Dostupné","Dálkové tratě","Airbus");
    }

    @Test
    void setSpolecnostID() {
        setLetadlo();
        Letadlo letadlo1 = letadlo;
        letadlo1.setSpolecnostID(3);
        assertEquals(3,letadlo1.getSpolecnostID());
    }

    @Test
    void setNazev() {
        setLetadlo();
        Letadlo letadlo1 = letadlo;
        letadlo1.setNazev("Airbus A199");
        assertEquals("Airbus A199",letadlo1.getNazev());
    }

    @Test
    void setRok_vyroby() {
        setLetadlo();
        Letadlo letadlo1 = letadlo;
        letadlo1.setRok_vyroby("1999");
        assertEquals("1999",letadlo1.getRok_vyroby());
    }

    @Test
    void setStav() {
        setLetadlo();
        Letadlo letadlo1 = letadlo;
        letadlo1.setStav("Porucha");
        assertEquals("Porucha",letadlo1.getStav());
    }

    @Test
    void setTyp() {
        setLetadlo();
        Letadlo letadlo1 = letadlo;
        letadlo1.setTyp("Krátké tratě");
        assertEquals("Krátké tratě",letadlo1.getTyp());
    }

    @Test
    void setVyrobce() {
        setLetadlo();
        Letadlo letadlo1 = letadlo;
        letadlo1.setVyrobce("ČSA");
        assertEquals("ČSA",letadlo1.getVyrobce());
    }
}