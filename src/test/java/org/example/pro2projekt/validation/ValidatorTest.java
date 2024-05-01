package org.example.pro2projekt.validation;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    @DisplayName("Validace údajů uživatele")
    public void isValid() {
        Validator validator = new Validator();
        assertTrue(validator.isValid("Jan", "Kubicek", "jenik1ku@seznam.cz", "030303/3421", "232223323"));
        assertFalse(validator.isValid("Ja", "Kubicek", "jenik1ku@seznam.cz", "030303/3421", "232223323"));
        assertFalse(validator.isValid("Jan", "Kubi", "jenik1@kuseznam.cz", "030303/3421", "232223323"));
        assertFalse(validator.isValid("Jan", "Kubicek", "jenik1kuseznam.cz", "030303/3421", "232223323"));
        assertFalse(validator.isValid("Jan", "Kubicek", "jenik1ku@seznam.cz", "030303/3", "232223323"));
        assertFalse(validator.isValid("Jan", "Kubicek", "jenik1ku@seznam.cz", "030303/3421", "2322233"));
    }

    @Test
    @DisplayName("Validace údajů letadla")
    public void isValidLetadlo() {
        Validator validator = new Validator();
        assertTrue(validator.isValidLetadlo("JAS 39-GRIPEN", "2015", "Dostupný", "Bojové", "JAS"));
        assertFalse(validator.isValidLetadlo("MI", "1990", "Porucha", "Bojové", "MIG"));
        assertFalse(validator.isValidLetadlo("MIG 29", "199", "Porucha", "Bojové", "MIG"));
        assertFalse(validator.isValidLetadlo("MIG 29", "1990", "Un", "Bojové", "MIG"));
        assertFalse(validator.isValidLetadlo("MIG 29", "1990", "Porucha", "Un", "MIG"));
        assertFalse(validator.isValidLetadlo("MIG 29", "1990", "Porucha", "Bojové", "Un"));
    }

    @Test
    @DisplayName("Validace údajů letiště")
    public void isValidLetiste() {
        Validator validator = new Validator();
        assertTrue(validator.isValidLetiste("Praha", 30, "Letiště V. H.", "Česká republika"));
        assertFalse(validator.isValidLetiste("HK", 30, "Letiště V. H.", "Česká republika"));
        assertFalse(validator.isValidLetiste("Hradec Králové", 19, "Letiště V. H.", "Česká republika"));
        assertFalse(validator.isValidLetiste("Hradec Králové", 30, "VH", "Česká republika"));
        assertFalse(validator.isValidLetiste("Hradec Králové", 30, "Letiště V. H.", "ČR"));
    }

    @Test
    @DisplayName("Validace údajů společnosti")
    public void isValidSpolecnost(){
        Validator validator = new Validator();
        assertTrue(validator.isValidSpolecnost("Wizz Airlines","Praha"));
        assertFalse(validator.isValidSpolecnost("CA","Praha"));
        assertFalse(validator.isValidSpolecnost("CSA","Pr"));
    }

}
