package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpolecnostTest {
    Spolecnost spolecnost;

    private void  setSpolecnost(){
        this.spolecnost = new Spolecnost("Wizz Air","Dublin");
    }

    @Test
    void setSpolecnostID() {
        setSpolecnost();
        Spolecnost spolecnost1 = this.spolecnost;
        spolecnost1.setSpolecnostID(3);
        assertEquals(3,spolecnost1.getSpolecnostID());
    }

    @Test
    void setNazev() {
        setSpolecnost();
        Spolecnost spolecnost1 = this.spolecnost;
        spolecnost1.setNazev("ČSA");
        assertEquals("ČSA",spolecnost1.getNazev());
    }

    @Test
    void setSidlo() {
        setSpolecnost();
        Spolecnost spolecnost1 = this.spolecnost;
        spolecnost1.setSidlo("Prague");
        assertEquals("Prague",spolecnost1.getSidlo());
    }
}