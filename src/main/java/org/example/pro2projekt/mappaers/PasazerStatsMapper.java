package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.PasazerStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasazerStatsMapper implements RowMapper<PasazerStats> {
    @Override
    public PasazerStats mapRow(ResultSet rs, int rowNum) throws SQLException {
        PasazerStats pasazerStats = new PasazerStats();
        pasazerStats.setTyp(rs.getString("TYP"));
        pasazerStats.setPocet(rs.getInt("POCET"));
        pasazerStats.setPopis(rs.getString("POPIS"));
        pasazerStats.setAllPasazers(rs.getInt("ALLPASAZERS"));
        return pasazerStats;
    }
}
