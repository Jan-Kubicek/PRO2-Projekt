package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetadloMapper;
import org.example.pro2projekt.mappaers.LetadloStatsMapper;
import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.objects.LetadloStats;
import org.example.pro2projekt.repository.LetadloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetadloStatsServiceImpl implements LetadloStatsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<LetadloStats> groupByVyrobces() {
        String query = "SELECT L.Vyrobce, L.Nazev, (SELECT COUNT(L1.LetadloID) FROM Letadlo L1 WHERE L1.Nazev = L.Nazev ) AS Pocet,\n" +
                "(SELECT COUNT(L1.LetadloID) FROM Letadlo L1 ) AS Allplanes\n" +
                "FROM Letadlo L GROUP BY L.Nazev";
        return jdbcTemplate.query(query, new LetadloStatsMapper());
    }
}
