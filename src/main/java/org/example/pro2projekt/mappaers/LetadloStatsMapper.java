package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.objects.LetadloStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetadloStatsMapper implements RowMapper<LetadloStats> {
    @Override
    public LetadloStats mapRow(ResultSet rs, int rowNum) throws SQLException {
        LetadloStats letadlo = new LetadloStats();
        letadlo.setVyrobce(rs.getString("VYROBCE"));
        letadlo.setNazev(rs.getString("NAZEV"));
        letadlo.setPocet(rs.getInt("POCET"));
        letadlo.setAllPlanes(rs.getInt("ALLPLANES"));
        return letadlo;
    }
}
