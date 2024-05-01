package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZavazadloTest {
    Zavazadlo zavazadlo;

    private void setZavazadlo(){
        this.zavazadlo = new Zavazadlo(1,1,0,30,30,15,30);
    }

    @Test
    void setZavazadloID() {
        setZavazadlo();
        Zavazadlo zavazadlo1 = this.zavazadlo;
        zavazadlo1.setZavazadloID(32);
        assertEquals(32,zavazadlo1.getZavazadloID());
    }

    @Test
    void setPasazerID() {
        setZavazadlo();
        Zavazadlo zavazadlo1 = this.zavazadlo;
        zavazadlo1.setPasazerID(4);
        assertEquals(4,zavazadlo1.getPasazerID());
    }

    @Test
    void setTyp_zavazadlaID() {
        setZavazadlo();
        Zavazadlo zavazadlo1 = this.zavazadlo;
        zavazadlo1.setTyp_zavazadlaID(0);
        assertEquals(0,zavazadlo1.getTyp_zavazadlaID());
    }

    @Test
    void setKrehke() {
        setZavazadlo();
        Zavazadlo zavazadlo1 = this.zavazadlo;
        zavazadlo1.setKrehke(1);
        assertEquals(1,zavazadlo1.getKrehke());
    }

    @Test
    void setSirka() {
        setZavazadlo();
        Zavazadlo zavazadlo1 = this.zavazadlo;
        zavazadlo1.setSirka(45);
        assertEquals(45,zavazadlo1.getSirka());
    }

    @Test
    void setVyska() {
        setZavazadlo();
        Zavazadlo zavazadlo1 = this.zavazadlo;
        zavazadlo1.setVyska(45);
        assertEquals(45,zavazadlo1.getVyska());
    }

    @Test
    void setVaha() {
        setZavazadlo();
        Zavazadlo zavazadlo1 = this.zavazadlo;
        zavazadlo1.setVaha(5);
        assertEquals(5,zavazadlo1.getVaha());
    }

    @Test
    void setHloubka() {
        setZavazadlo();
        Zavazadlo zavazadlo1 = this.zavazadlo;
        zavazadlo1.setHloubka(45);
        assertEquals(45,zavazadlo1.getHloubka());
    }
}