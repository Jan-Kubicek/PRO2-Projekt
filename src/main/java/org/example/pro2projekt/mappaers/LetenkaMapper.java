package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.objects.Letenka;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetenkaMapper implements RowMapper<Letenka> {
    @Override
    public Letenka mapRow(ResultSet rs, int rowNum) throws SQLException {
        Letenka letenka = new Letenka();
        letenka.setLetenkaID(rs.getInt("LETENKAID"));
        letenka.setJeSkupinova(rs.getInt("JESKUPINOVA"));
        letenka.setLetID(rs.getInt("LETID"));
        letenka.setTrida(rs.getString("TRIDA"));
        letenka.setPasazerID(rs.getInt("PASAZERID"));
        letenka.setPocet_pasazeru(rs.getInt("POCET_PASAZERU"));
        return letenka;
    }
}
