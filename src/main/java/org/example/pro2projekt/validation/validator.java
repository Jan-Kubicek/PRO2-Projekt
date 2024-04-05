package org.example.pro2projekt.validation;

import com.vaadin.flow.component.notification.Notification;

import org.example.pro2projekt.controller.dataInput;
import org.example.pro2projekt.controller.dataUpload;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validator {
    dataUpload upload = new dataUpload();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public validator(){
    }

    public boolean Registruj(String jmeno, String prijmeni, String email,String rodCi, String telCis, String heslo, String pohlavi){
        System.out.println(jmeno + " " + prijmeni + " " + email + " " + rodCi + " " + telCis + " " + heslo + " " + pohlavi);
        if((jmeno.length() < 3) || (prijmeni.length() < 5) || (rodCi.length() < 9) || (telCis.length() < 8) || (heslo.length() < 4) || (email.length() < 5) ){
            return false;
        }
        if((!(pohlavi.equals("Muž"))) && (!(pohlavi.equals("Žena")))){
            return false;
        }
        if(!isValidEmail(email)){
            return false;
        }
        try{
            int value1 = Integer.parseInt(rodCi);
            int value2 = Integer.parseInt(telCis);
            rodCi = rodCi.substring(0,6)+ "/"+ rodCi.substring(6);
            upload.Register(jmeno,prijmeni,email,rodCi,telCis,heslo,pohlavi);
            return true;

        }catch (Exception e) {
            return false;
        }
    }

    private boolean isValidEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean isValid(String jmeno, String prijmeni, String email,String rodCi, String telCis){
        System.out.println(jmeno + " " + prijmeni + " " + email + " " + rodCi + " " + telCis );
        if((jmeno.length() < 3) || (prijmeni.length() < 5) || (rodCi.length() < 9) || (telCis.length() < 8) ||  (email.length() < 5) ){
            return false;
        }
        if(!isValidEmail(email)){
            return false;
        }
        try{
            int value2 = Integer.parseInt(telCis);
            return true;

        }catch (Exception e) {
            return false;
        }
    }

    public boolean isValidLetadlo(String nazev, String rok, String stav, String typ, String vyrobce){
        if((nazev.length() < 3) || (rok.length() < 3) || (stav.length() < 3) || (typ.length() < 3) || (vyrobce.length() < 3)){
            return false;
        }
        try{
            int value = Integer.parseInt(rok);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean isValidLetiste(String mesto, int kapacita, String nazev, String stat){
        if((mesto.length() < 3) || (kapacita < 3) || (nazev.length() < 4) || (stat.length() < 4)){
            return false;
        }else{
            return  true;
        }
    }

}
