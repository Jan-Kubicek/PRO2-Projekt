package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetadloStatsMapper;
import org.example.pro2projekt.mappaers.LetenkaHistorieMapper;
import org.example.pro2projekt.objects.LetenkaHistorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetenkaHistorieServiceImpl implements LetenkaHistorieService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<LetenkaHistorie> findByLetID(int letID) {
        String query = "SELECT L.LetID AS LetId, L.LetadloID AS LetadloID, L.Cas_odletu AS Cas_Odletu,\n" +
                "LT1.Mesto AS MestoOdletu, LT1.Stat AS StatOdletu, LT1.Nazev AS NazevLOdletu, LT2.Mesto AS MestoPriletu,\n" +
                "LT2.Stat AS StatPriletu, LT2.Nazev AS NazevLPriletu FROM Let L\n" +
                "JOIN Smerletu S ON L.SmerletuID = S.SmerletuID\n" +
                "JOIN letistesmerletu LSL ON S.SmerletuID = LSL.SmerletuID\n" +
                "JOIN letistesmerletu LSL1 ON S.SmerletuID = LSL1.SmerletuID\n" +
                "JOIN Letiste LT1 ON LSL.LetisteID = LT1.LetisteID\n" +
                "JOIN Letiste LT2 ON LSL1.LetisteID = LT2.LetisteID\n" +
                "JOIN Letadlo ltdl ON L.LetadloID = ltdl.LetadloID\n" +
                "JOIN tridaletadlo tldl ON ltdl.LetadloID = tldl.LetadloID\n" +
                "JOIN Trida t ON tldl.TridaID = t.TridaID\n" +
                "WHERE (LSL.LetisteID != LSL1.LetisteID ) AND L.LetID = ? \n" +
                "GROUP BY  L.LetID,  L.LetadloID,LT1.Mesto, +LT1.Stat,LT1.Nazev,LT2.Mesto,LT2.Stat,LT2.Nazev";
        return jdbcTemplate.query(query,new LetenkaHistorieMapper(),letID);
    }

    @Override
    public List<LetenkaHistorie> findAll() {
        String query = "SELECT L.LetID AS LetId, L.LetadloID AS LetadloID, L.Cas_odletu AS Cas_Odletu,\n" +
                "LT1.Mesto AS MestoOdletu, LT1.Stat AS StatOdletu, LT1.Nazev AS NazevLOdletu, LT2.Mesto AS MestoPriletu,\n" +
                "LT2.Stat AS StatPriletu, LT2.Nazev AS NazevLPriletu FROM Let L\n" +
                "JOIN Smerletu S ON L.SmerletuID = S.SmerletuID\n" +
                "JOIN letistesmerletu LSL ON S.SmerletuID = LSL.SmerletuID\n" +
                "JOIN letistesmerletu LSL1 ON S.SmerletuID = LSL1.SmerletuID\n" +
                "JOIN Letiste LT1 ON LSL.LetisteID = LT1.LetisteID\n" +
                "JOIN Letiste LT2 ON LSL1.LetisteID = LT2.LetisteID\n" +
                "JOIN Letadlo ltdl ON L.LetadloID = ltdl.LetadloID\n" +
                "JOIN tridaletadlo tldl ON ltdl.LetadloID = tldl.LetadloID\n" +
                "JOIN Trida t ON tldl.TridaID = t.TridaID\n" +
                "WHERE (LSL.LetisteID != LSL1.LetisteID )\n"+
                "GROUP BY  L.LetID,  L.LetadloID,LT1.Mesto, +LT1.Stat,LT1.Nazev,LT2.Mesto,LT2.Stat,LT2.Nazev";
        return jdbcTemplate.query(query,new LetenkaHistorieMapper());
    }
}
