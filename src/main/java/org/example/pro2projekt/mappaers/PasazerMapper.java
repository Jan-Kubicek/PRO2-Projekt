package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.Pasazer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasazerMapper implements RowMapper<Pasazer> {
    @Override
    public Pasazer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pasazer pasazer = new Pasazer();
        pasazer.setPasazerID(rs.getInt("PasazerID"));
        pasazer.setEmail(rs.getString("Email"));
        pasazer.setHeslo(rs.getString("Heslo"));
        pasazer.setJmeno(rs.getString("Jmeno"));
        pasazer.setPrijmeni(rs.getString("Prijmeni"));
        pasazer.setDatum_narozeni(rs.getDate("Datum_narozeni"));
        pasazer.setPohlavi(rs.getInt("Pohlavi"));
        pasazer.setRodne_cislo(rs.getString("Rodne_cislo"));
        pasazer.setTelefoni_cislo(rs.getString("Telefoni_cislo"));
        pasazer.setTyp_pasazeraID(rs.getInt("Typ_pasazeraID"));
        return pasazer;
    }
}
