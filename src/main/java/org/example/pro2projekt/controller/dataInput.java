package org.example.pro2projekt.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dataInput {
        Database database;
    public dataInput(){
         database = new Database();
    }

    public String[] getAllStates(){
        ResultSet set = database.getData("SELECT Stat FROM Letiste");
        String[] states;
        try {
            set.last();
            int rows = set.getRow();
            states = new String[rows];

            // Vrátit se na začátek result setu
            set.beforeFirst();

            // Procházet výsledky a ukládat je do pole
            int i = 0;
            while (set.next()) {
                states[i++] = set.getString("Stat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            states = new String[0];
        }

        return states;
    }
    public String[] getAllClasses(){
        ResultSet set = database.getData("SELECT Nazev FROM Trida");
        String[] states;
        try {
            set.last();
            int rows = set.getRow();
            states = new String[rows];

            // Vrátit se na začátek result setu
            set.beforeFirst();

            // Procházet výsledky a ukládat je do pole
            int i = 0;
            while (set.next()) {
                states[i++] = set.getString("Nazev");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            states = new String[0];
        }

        return states;
    }

    public ResultSet getLets(String stat1, String stat2){
        ResultSet set = database.getData("SELECT L.LetID AS LetId, L.LetadloID AS LetadloID, L.Cas_odletu AS Cas_Odletu, L.Cas_priletu AS Cas_Priletu,\n" +
                "LT1.Mesto AS MestoOdletu, LT1.Stat AS StatOdletu, LT1.Nazev AS NazevLOdletu, LT2.Mesto AS MestoPriletu,\n" +
                "LT2.Stat AS StatPriletu, LT2.Nazev AS NazevLPriletu\n" +
                " FROM Let L\n" +
                "JOIN Smerletu S ON L.SmerletuID = S.SmerletuID\n" +
                "JOIN letistesmerletu LSL ON S.SmerletuID = LSL.SmerletuID\n" +
                "JOIN letistesmerletu LSL1 ON S.SmerletuID = LSL1.SmerletuID\n" +
                "JOIN Letiste LT1 ON LSL.LetisteID = LT1.LetisteID\n" +
                "JOIN Letiste LT2 ON LSL1.LetisteID = LT2.LetisteID\n" +
                "WHERE (LSL.LetisteID != LSL1.LetisteID ) AND (LT1.Stat = '"+stat1+"' AND LT2.Stat = '"+stat2+"')");
        return set;
    }
}
