package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetenkaTest {

    Letenka letenka ;

    private void setLetenka(){
        this.letenka = new Letenka(5,12,0,3,"Prvni");
    }

    @Test
    void setLetenkaID() {
        setLetenka();
        Letenka letenka1 = this.letenka;
        letenka1.setLetenkaID(10);
        assertEquals(10,letenka1.getLetenkaID());
    }

    @Test
    void setLetID() {
        setLetenka();
        Letenka letenka1 = this.letenka;
        letenka1.setLetID(10);
        assertEquals(10,letenka1.getLetID());
    }

    @Test
    void setPasazerID() {
        setLetenka();
        Letenka letenka1 = this.letenka;
        letenka1.setPasazerID(20);
        assertEquals(20,letenka1.getPasazerID());
    }

    @Test
    void setJeSkupinova() {
        setLetenka();
        Letenka letenka1 = this.letenka;
        letenka1.setJeSkupinova(1);
        assertEquals(1,letenka1.getJeSkupinova());
    }

    @Test
    void setPocet_pasazeru() {
        setLetenka();
        Letenka letenka1 = this.letenka;
        letenka1.setPocet_pasazeru(4);
        assertEquals(4,letenka1.getPocet_pasazeru());
    }

    @Test
    void setTrida() {
        setLetenka();
        Letenka letenka1 = this.letenka;
        letenka1.setTrida("Bussiness");
        assertEquals("Bussiness",letenka1.getTrida());
    }
}