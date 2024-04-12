package org.example.pro2projekt.service;

import jakarta.persistence.Access;
import org.example.pro2projekt.mappaers.LetenkaMapper;
import org.example.pro2projekt.objects.Letenka;
import org.example.pro2projekt.repository.LetenkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LetenkaServiceImpl implements LetenkaService {
    @Autowired
    private LetenkaRepository letenkaRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Letenka> findByPasazer(int id) {
        String query = "SELECT * FROM Letenka L WHERE L.PasazerID = ?";
        return jdbcTemplate.query(query,new LetenkaMapper(), id);
    }

    @Override
    public List<Letenka> findByLet(int id) {
        String query = "SELECT * FROM Letenka L WHERE L.LetID = ?";
        return jdbcTemplate.query(query,new LetenkaMapper(),id);
    }

    @Override
    public List<Letenka> findByPasazerTrida(int id, String trida) {
        String query = "SELECT * FROM Letenka L WHERE L.PasazerID = ? AND L.Trida = ?";
        return jdbcTemplate.query(query, new LetenkaMapper(), id, trida);
    }

    @Override
    public List<String> getAllStates() {
        String query = "SELECT DISTINCT L.Stat FROM Letiste L";
        return jdbcTemplate.queryForList(query, String.class);
    }
    @Override
    public List<String> getAllClasses() {
        String query = "SELECT DISTINCT T.Nazev FROM Trida T";
        return jdbcTemplate.queryForList(query,String.class);
    }

    @Override
    public void createNewLetenka(int letId, int pasazerId, int jeSkupinova, int pocet_Pasazeru, String Trida) {
        String query = "INSERT INTO Letenka (LetID,PasazerID, jeSkupinova, Pocet_pasazeru,Trida) VALUES (?,?,?,?,?)";
        jdbcTemplate.query(query,new LetenkaMapper(),letId,pasazerId,jeSkupinova,pocet_Pasazeru,Trida);
    }

    @Override
    public void deleteLetenka(int letenkaID) {
        String query = "DELETE FROM Letenka WHERE Letenka.LetenkaID = ?";
        jdbcTemplate.query(query,new LetenkaMapper(),letenkaID);
    }

}
