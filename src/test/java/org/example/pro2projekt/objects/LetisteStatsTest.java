package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetisteStatsTest {
    LetisteStats letisteStats;

    private void setLetisteStats(){
        this.letisteStats = new LetisteStats("Polsko",2,130,520);
    }

    @Test
    void setStat() {
        setLetisteStats();
        LetisteStats letisteStats1 = this.letisteStats;
        letisteStats1.setStat("Dánsko");
        assertEquals("Dánsko",letisteStats1.getStat());
    }

    @Test
    void setPocet() {
        setLetisteStats();
        LetisteStats letisteStats1 = this.letisteStats;
        letisteStats1.setPocet(5);
        assertEquals(5,letisteStats1.getPocet());
    }

    @Test
    void setKapacita() {
        setLetisteStats();
        LetisteStats letisteStats1 = this.letisteStats;
        letisteStats1.setKapacita(100);
        assertEquals(100,letisteStats1.getKapacita());
    }

    @Test
    void setAllLetiste() {
        setLetisteStats();
        LetisteStats letisteStats1 = this.letisteStats;
        letisteStats1.setAllLetiste(550);
        assertEquals(550,letisteStats1.getAllLetiste());
    }
}