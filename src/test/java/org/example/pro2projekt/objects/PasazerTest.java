package org.example.pro2projekt.objects;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PasazerTest {
    Pasazer pasazer;

    private void setPasazer(){
        this.pasazer = new Pasazer(1,new Date(2003,4,8),"email@email.cz","heslo","Jan",0,"Kubicek","040404/2321","123456789");
    }

    @Test
    void setPasazerID() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setPasazerID(2);
        assertEquals(2,pasazer1.getPasazerID());
    }

    @Test
    void setTyp_pasazeraID() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setTyp_pasazeraID(3);
        assertEquals(3,pasazer1.getTyp_pasazeraID());
    }

    @Test
    void setDatum_narozeni() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setDatum_narozeni(new Date(2003,4,8));
        assertEquals(new Date(2003,4,8),pasazer1.getDatum_narozeni());
    }

    @Test
    void setEmail() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setEmail("email@seznam.cz");
        assertEquals("email@seznam.cz",pasazer1.getEmail());
    }

    @Test
    void setHeslo() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setHeslo("12345");
        assertEquals("12345",pasazer1.getHeslo());
    }

    @Test
    void setJmeno() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setJmeno("Jakub");
        assertEquals("Jakub",pasazer1.getJmeno());
    }

    @Test
    void setPohlavi() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setPohlavi(1);
        assertEquals(1,pasazer1.getPohlavi());
    }

    @Test
    void setPrijmeni() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setPrijmeni("Novotny");
        assertEquals("Novotny",pasazer1.getPrijmeni());
    }

    @Test
    void setRodne_cislo() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setRodne_cislo("010101/0101");
        assertEquals("010101/0101",pasazer1.getRodne_cislo());
    }

    @Test
    void setTelefoni_cislo() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setTelefoni_cislo("012345678");
        assertEquals("012345678",pasazer1.getTelefoni_cislo());
    }


    @Test
    void getPassword() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setHeslo("a123");
        assertEquals(pasazer1.getPassword(),pasazer1.getHeslo());
    }

    @Test
    void getUsername() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        pasazer1.setEmail("email@gmail.cz");
        assertEquals(pasazer1.getUsername(),pasazer1.getEmail());
    }

    @Test
    void getRoles() {
        setPasazer();
        Pasazer pasazer1 = this.pasazer;
        assertEquals("ROLE_CLIENT",pasazer1.getRoles());
    }
}