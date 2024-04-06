package org.example.pro2projekt.mappaers;


import org.example.pro2projekt.objects.LetadloStats;
import org.example.pro2projekt.objects.LetisteStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetisteStatsMapper implements RowMapper<LetisteStats> {
    @Override
    public LetisteStats mapRow(ResultSet rs, int rowNum) throws SQLException {
        LetisteStats letisteStats = new LetisteStats();
        letisteStats.setStat(rs.getString("STAT"));
        letisteStats.setPocet(rs.getInt("POCET"));
        letisteStats.setKapacita(rs.getInt("KAPACITA"));
        letisteStats.setAllLetiste(rs.getInt("AllLetiste"));
        return letisteStats;
    }
}
