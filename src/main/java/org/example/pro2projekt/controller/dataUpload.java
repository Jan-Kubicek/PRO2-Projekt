package org.example.pro2projekt.controller;

import org.mindrot.jbcrypt.BCrypt;

public class dataUpload {
    Database database;

    public dataUpload() {
        database = new Database();
    }

    public void Register(String jmeno, String prijmeni, String email, String rodCi, String telCis, String heslo, String pohlavi) {
        String hashedPassword = BCrypt.hashpw(heslo, BCrypt.gensalt());
        int poh = 1; // žena
        if (pohlavi.equals("Muž"))
            poh = 0;
        String sql = "INSERT INTO Pasazer (Typ_pasazeraID, Datum_narozeni, Email, Heslo, Jmeno, Pohlavi, Prijmeni, Rodne_cislo, Telefoni_cislo)\n" +
                "VALUES ('1', '2024-04-03', '" + email + "', '" + hashedPassword + "', '" + jmeno + "', '" + poh + "', '" + prijmeni + "', '" + rodCi + "', '" + telCis + "')";
        database.newEntity(sql);
    }
}
