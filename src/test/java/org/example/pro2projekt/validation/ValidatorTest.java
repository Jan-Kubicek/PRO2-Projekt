package org.example.pro2projekt.validation;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void isValid(){
        Validator validator = new Validator();
        assertTrue(validator.isValid("Jan", "Kubicek", "jenik1ku@seznam.cz", "030303/3421", "232223323"));
        assertFalse(validator.isValid("Ja", "Kubicek", "jenik1ku@seznam.cz", "030303/3421", "232223323"));
        assertFalse(validator.isValid("Jan", "Kubi", "jenik1@kuseznam.cz", "030303/3421", "232223323"));
        assertFalse(validator.isValid("Jan", "Kubicek", "jenik1kuseznam.cz", "030303/3421", "232223323"));
        assertFalse(validator.isValid("Jan", "Kubicek", "jenik1ku@seznam.cz", "030303/3", "232223323"));
        assertFalse(validator.isValid("Jan", "Kubicek", "jenik1ku@seznam.cz", "030303/3421", "2322233"));
    }
}
