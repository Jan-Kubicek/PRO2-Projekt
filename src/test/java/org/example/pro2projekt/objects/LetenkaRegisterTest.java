package org.example.pro2projekt.objects;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class LetenkaRegisterTest {
    LetenkaRegister letenkaRegister;

    private void setLetenkaRegister() {
        this.letenkaRegister = new LetenkaRegister(10, 10, new Date(2003, 12, 4), new Date(2003, 12, 4), "Praha", "Česká Republika", "Letiště Václava Havla", "Tokio", "Japonsko", "Narrita");
    }

    @Test
    void setTrida() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setTrida("Prvni");
        assertEquals("Prvni", letenkaRegister1.getTrida());
    }

    @Test
    void setLetId() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setLetId(20);
        assertEquals(20, letenkaRegister1.getLetId());
    }

    @Test
    void setLetadloID() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setLetadloID(10);
        assertEquals(10, letenkaRegister1.getLetadloID());
    }

    @Test
    void setCas_Odletu() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setCas_Odletu( new Date(2023,12,3));
        assertEquals(new Date(2023,12,3),letenkaRegister1.getCas_Odletu());
    }

    @Test
    void setCas_Priletu() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setCas_Priletu(new Date(2022,5,3));
        assertEquals(new Date(2022,5,3),letenkaRegister1.getCas_Priletu());
    }

    @Test
    void setMestoOdletu() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setMestoOdletu("Pardubice");
        assertEquals("Pardubice",letenkaRegister1.getMestoOdletu());
    }

    @Test
    void setStatOdletu() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setStatOdletu("Polsko");
        assertEquals("Polsko",letenkaRegister1.getStatOdletu());
    }

    @Test
    void setNazevLOdletu() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setNazevLOdletu("Letiště M.R.Š");
        assertEquals("Letiště M.R.Š",letenkaRegister1.getNazevLOdletu());
    }

    @Test
    void setMestoPriletu() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setMestoPriletu("Edo");
        assertEquals("Edo",letenkaRegister1.getMestoPriletu());
    }

    @Test
    void setStatPriletu() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setStatPriletu("Shogunat");
        assertEquals("Shogunat",letenkaRegister1.getStatPriletu());
    }

    @Test
    void setNazevLPriletu() {
        setLetenkaRegister();
        LetenkaRegister letenkaRegister1 = this.letenkaRegister;
        letenkaRegister1.setNazevLPriletu("Letiště edo");
        assertEquals("Letiště edo",letenkaRegister1.getNazevLPriletu());
    }
}