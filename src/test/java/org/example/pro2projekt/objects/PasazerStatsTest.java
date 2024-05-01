package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasazerStatsTest {
    PasazerStats pasazerStats;

    private void setPasazerStats(){
        this.pasazerStats = new PasazerStats("Dospělý",30,"Věk > 18 AND < 65",250);
    }

    @Test
    void setTyp() {
        setPasazerStats();
        PasazerStats pasazerStats1 = this.pasazerStats;
        pasazerStats1.setTyp("Mládežník");
        assertEquals("Mládežník",pasazerStats1.getTyp());
    }

    @Test
    void setPocet() {
        setPasazerStats();
        PasazerStats pasazerStats1 = this.pasazerStats;
        pasazerStats1.setPocet(25);
        assertEquals(25,pasazerStats1.getPocet());
    }

    @Test
    void setPopis() {
        setPasazerStats();
        PasazerStats pasazerStats1 = this.pasazerStats;
        pasazerStats1.setPopis("Věk > 20");
        assertEquals("Věk > 20",pasazerStats1.getPopis());
    }

    @Test
    void setAllPasazers() {
        setPasazerStats();
        PasazerStats pasazerStats1 = this.pasazerStats;
        pasazerStats1.setAllPasazers(230);
        assertEquals(230,pasazerStats1.getAllPasazers());
    }
}