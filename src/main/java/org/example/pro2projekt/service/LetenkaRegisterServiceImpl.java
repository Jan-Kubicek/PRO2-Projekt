package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetenkaMapper;
import org.example.pro2projekt.mappaers.LetenkaRegisterMapper;
import org.example.pro2projekt.objects.Letenka;
import org.example.pro2projekt.objects.LetenkaRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LetenkaRegisterServiceImpl implements LetenkaRegisterService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<LetenkaRegister> findByStatesDateNumberClass(String statOdletu, String statPriletum, Date datum, int NumOfMembers, String trida) {
        String query = "SELECT L.LetID AS LetId, L.LetadloID AS LetadloID, L.Cas_odletu AS Cas_Odletu, L.Cas_priletu AS Cas_Priletu,\n" +
                "LT1.Mesto AS MestoOdletu, LT1.Stat AS StatOdletu, LT1.Nazev AS NazevLOdletu, LT2.Mesto AS MestoPriletu,\n" +
                "LT2.Stat AS StatPriletu, LT2.Nazev AS NazevLPriletu, t.Nazev AS Trida\n" +
                " FROM Let L\n" +
                "JOIN Smerletu S ON L.SmerletuID = S.SmerletuID\n" +
                "JOIN letistesmerletu LSL ON S.SmerletuID = LSL.SmerletuID\n" +
                "JOIN letistesmerletu LSL1 ON S.SmerletuID = LSL1.SmerletuID\n" +
                "JOIN Letiste LT1 ON LSL.LetisteID = LT1.LetisteID\n" +
                "JOIN Letiste LT2 ON LSL1.LetisteID = LT2.LetisteID\n" +
                "JOIN Letadlo ltdl ON L.LetadloID = ltdl.LetadloID\n" +
                "JOIN tridaletadlo tldl ON ltdl.LetadloID = tldl.LetadloID\n" +
                "JOIN Trida t ON tldl.TridaID = t.TridaID\n" +
                "WHERE (LSL.LetisteID != LSL1.LetisteID ) AND (LT1.Stat = ? AND LT2.Stat = ?) AND (L.Cas_odletu >= ?) AND (t.Nazev >= ?)" +
                "GROUP BY  L.LetID, \n" +
                "    L.LetadloID,\n" +
                "    LT1.Mesto,\n" +
                "    LT1.Stat,\n" +
                "    LT1.Nazev,\n" +
                "    LT2.Mesto,\n" +
                "    LT2.Stat,\n" +
                "    LT2.Nazev";
        return jdbcTemplate.query(query,new LetenkaRegisterMapper(),statOdletu,statPriletum,datum,trida);
    }

    @Override
    public List<LetenkaRegister> findAll() {
        String query = "SELECT L.LetID AS LetId, L.LetadloID AS LetadloID, L.Cas_odletu AS Cas_Odletu,L.Cas_priletu AS Cas_Priletu,LT1.Mesto AS MestoOdletu, LT1.Stat AS StatOdletu, LT1.Nazev AS NazevLOdletu, LT2.Mesto AS MestoPriletu, LT2.Stat AS StatPriletu, LT2.Nazev AS NazevLPriletu, t.Nazev AS Trida FROM Let L JOIN Smerletu S ON L.SmerletuID = S.SmerletuID JOIN letistesmerletu LSL ON S.SmerletuID = LSL.SmerletuID JOIN letistesmerletu LSL1 ON S.SmerletuID = LSL1.SmerletuID JOIN Letiste LT1 ON LSL.LetisteID = LT1.LetisteID JOIN Letiste LT2 ON LSL1.LetisteID = LT2.LetisteID JOIN Letadlo ltdl ON L.LetadloID = ltdl.LetadloID JOIN tridaletadlo tldl ON ltdl.LetadloID = tldl.LetadloID JOIN Trida t ON tldl.TridaID = t.TridaID WHERE (LSL.LetisteID != LSL1.LetisteID ) GROUP BY  L.LetID,  L.LetadloID,LT1.Mesto, LT1.Stat,LT1.Nazev,LT2.Mesto,LT2.Stat,LT2.Nazev,t.Nazev";
        return jdbcTemplate.query(query,new LetenkaRegisterMapper());
    }

}
