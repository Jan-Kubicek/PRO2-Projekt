package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.PasazerStatsMapper;
import org.example.pro2projekt.objects.PasazerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasazerStatsServiceImpl implements PasazerStatsService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PasazerStats> groupByTyp() {
        String query = "SELECT TP.Nazev AS Typ, \n" +
                "(SELECT COUNT(P1.pasazerid) FROM Pasazer P1 WHERE P1.typ_pasazeraid = TP.Typ_pasazeraID) AS Pocet,\n" +
                "TP.Popis AS Popis,\n" +
                "(SELECT COUNT(P1.pasazerid) FROM Pasazer P1) AS AllPasazers\n" +
                "FROM Pasazer P \n" +
                "INNER JOIN Typ_pasazera TP ON P.typ_pasazeraid = TP.Typ_pasazeraID\n" +
                "GROUP BY TP.Typ_pasazeraID";
        return jdbcTemplate.query(query,new PasazerStatsMapper());
    }
}
