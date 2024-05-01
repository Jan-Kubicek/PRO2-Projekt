package org.example.pro2projekt.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public Validator() {
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValid(String jmeno, String prijmeni, String email, String rodCi, String telCis) {
        System.out.println(jmeno + " " + prijmeni + " " + email + " " + rodCi + " " + telCis);
        if ((jmeno.length() < 3) || (prijmeni.length() < 5) || (rodCi.length() < 9) || (telCis.length() < 8) || (email.length() < 5)) {
            return false;
        }
        if (!isValidEmail(email)) {
            return false;
        }
        try {
            int value2 = Integer.parseInt(telCis);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidLetadlo(String nazev, String rok, String stav, String typ, String vyrobce) {
        if ((nazev.length() < 3) || (rok.length() < 4) || (stav.length() < 3) || (typ.length() < 3) || (vyrobce.length() < 3)) {
            return false;
        }
        try {
            int value = Integer.parseInt(rok);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidLetiste(String mesto, int kapacita, String nazev, String stat) {
        return (mesto.length() >= 3) && (kapacita >= 20) && (nazev.length() >= 4) && (stat.length() >= 4);
    }

    public boolean isValidSpolecnost(String nazev, String sidlo) {
        return (nazev.length() >= 3) && (sidlo.length() >= 3);
    }

}
