package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetadloStatsMapper;
import org.example.pro2projekt.mappaers.LetisteStatsMapper;
import org.example.pro2projekt.objects.LetisteStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LetisteStatsServiceImpl implements LetisteStatsService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<LetisteStats> groupByStates() {
        String query = "SELECT L.Stat, (SELECT COUNT(L1.LetisteID) FROM Letiste L1 WHERE L1.Stat = L.Stat) AS Pocet,\n" +
                "(SELECT SUM(L1.Kapacita) FROM Letiste L1 WHERE L1.Stat = L.Stat) AS Kapacita,\n" +
                "(SELECT SUM(L1.Kapacita) FROM Letiste L1 ) AS AllLetiste\n" +
                " FROM Letiste L GROUP BY L.Stat";
        return jdbcTemplate.query(query,new LetisteStatsMapper());
    }
}
