package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetadloStatsTest {
    LetadloStats letadloStats;
    private void setLetadloStats(){
        this.letadloStats = new LetadloStats("Airbus","A 320",40,250);
    }

    @Test
    void setVyrobce() {
        setLetadloStats();
        LetadloStats letadloStats1 = letadloStats;
        letadloStats1.setVyrobce("JAS");
        assertEquals("JAS",letadloStats1.getVyrobce());
    }

    @Test
    void setNazev() {
        setLetadloStats();
        LetadloStats letadloStats1 = letadloStats;
        letadloStats1.setNazev("A 321");
        assertEquals("A 321",letadloStats1.getNazev());
    }

    @Test
    void setPocet() {
        setLetadloStats();
        LetadloStats letadloStats1 = letadloStats;
        letadloStats1.setPocet(50);
        assertEquals(50,letadloStats1.getPocet());
    }

    @Test
    void setAllPlanes() {
        setLetadloStats();
        LetadloStats letadloStats1 = letadloStats;
        letadloStats1.setAllPlanes(340);
        assertEquals(340,letadloStats1.getAllPlanes());
    }
}