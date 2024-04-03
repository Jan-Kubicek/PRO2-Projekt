package org.example.pro2projekt.controller;

import org.mindrot.jbcrypt.BCrypt;

public class dataUpload {
    Database database;
    public dataUpload(){
        database = new Database();
    }

    public void Register(String jmeno, String prijmeni, String email,String rodCi, String telCis, String heslo, String pohlavi){
        String hashedPassword = BCrypt.hashpw(heslo,BCrypt.gensalt());
        String sql = "INSERT INTO Pasazer (Typ_pasazeraID, Datum_narozeni, Email, Heslo, Jmeno, Pohlavi, Prijmeni, Rodne_cislo, Telefoni_cislo)\n" +
                "VALUES ('1', 'yyyy-mm-dd', '"+email+"', '"+hashedPassword+"', '"+jmeno+"', '"+pohlavi+"', '"+prijmeni+"', '"+rodCi+"', '"+telCis+"');";
        database.newEntity(sql);
    }
}
