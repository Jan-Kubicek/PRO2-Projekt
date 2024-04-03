package org.example.pro2projekt.validation;

import com.vaadin.flow.component.notification.Notification;

import org.example.pro2projekt.controller.dataInput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validator {

    private dataInput input = new dataInput();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public validator(){
    }

    public boolean Registruj(String jmeno, String prijmeni, String email,String rodCi, String telCis, String heslo, String pohlavi){
        if((jmeno.length() < 3) || (prijmeni.length() < 5) || (rodCi.length() < 10) || (telCis.length() < 9) || (heslo.length() < 4) || (email.length() < 5) ){
            return false;
        }
        if((!(pohlavi.equals("Muž"))) || (!(pohlavi.equals("Žena")))){
            return false;
        }
        if(!isValidEmail(email)){
            return false;
        }
        try{
            int value1 = Integer.parseInt(rodCi);
            int value2 = Integer.parseInt(telCis);

            rodCi = rodCi.substring(0,6)+ "/"+ rodCi.substring(6);

            return true;

        }catch (Exception e) {
            return false;
        }
    }

    private boolean isValidEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String hashedPassword(String heslo){


        return hashedPass;
    }
}
