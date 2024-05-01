package org.example.pro2projekt.objects;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LetenkaHistorieTest {
    LetenkaHistorie letenkaHistorie;

    private void setLetenkaHistorie() {
        this.letenkaHistorie = new LetenkaHistorie(10, 10, new Date(2003, 12, 4), "Praha", "Česká Republika", "Letiště Václava Havla", "Tokio", "Japonsko", "Narrita");
    }

    @Test
    void setLetId() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setLetId(2);
        assertEquals(2,letenkaHistorie1.getLetId());
    }

    @Test
    void setLetadloID() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setLetadloID(13);
        assertEquals(13,letenkaHistorie1.getLetadloID());
    }

    @Test
    void setCas_Odletu() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setCas_Odletu(new Date(2015,4,9));
        assertEquals(new Date(2015,4,9),letenkaHistorie1.getCas_Odletu());
    }

    @Test
    void setMestoOdletu() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setMestoOdletu("Brno");
        assertEquals("Brno",letenkaHistorie1.getMestoOdletu());
    }

    @Test
    void setStatOdletu() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setStatOdletu("Morava");
        assertEquals("Morava",letenkaHistorie1.getStatOdletu());
    }

    @Test
    void setNazevLOdletu() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setNazevLOdletu("Letiště VH");
        assertEquals("Letiště VH",letenkaHistorie1.getNazevLOdletu());
    }

    @Test
    void setMestoPriletu() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setMestoPriletu("Edo");
        assertEquals("Edo",letenkaHistorie1.getMestoPriletu());
    }

    @Test
    void setStatPriletu() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setStatPriletu("Shogunat");
        assertEquals("Shogunat",letenkaHistorie1.getStatPriletu());
    }

    @Test
    void setNazevLPriletu() {
        setLetenkaHistorie();
        LetenkaHistorie letenkaHistorie1 = this.letenkaHistorie;
        letenkaHistorie1.setNazevLPriletu("Nove");
        assertEquals("Nove",letenkaHistorie1.getNazevLPriletu());
    }
}