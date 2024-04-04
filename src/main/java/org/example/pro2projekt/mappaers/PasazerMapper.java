package org.example.pro2projekt.mappaers;
import org.example.pro2projekt.objects.Pasazer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class PasazerMapper implements RowMapper<Pasazer> {
    @Override
    public Pasazer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pasazer pasazer = new Pasazer();
        pasazer.setPasazerID(rs.getInt("ID"));
        pasazer.setEmail(rs.getString("EMAIL"));
        pasazer.setHeslo(rs.getString("HESLO"));
        pasazer.setJmeno(rs.getString("JMENO"));
        pasazer.setPrijmeni(rs.getString("PRIJMENI"));
        pasazer.setDatum_narozeni(rs.getDate("DATUM_NAROZENI"));
        pasazer.setPohlavi(rs.getInt("POHLAVI"));
        pasazer.setRodne_cislo(rs.getString("RODNE_CISLO"));
        pasazer.setTelefoni_cislo(rs.getString("TELEFONI_CISLO"));
        pasazer.setTyp_pasazeraID(rs.getInt("TYP_PASAZERAID"));
        return pasazer;
    }
}
