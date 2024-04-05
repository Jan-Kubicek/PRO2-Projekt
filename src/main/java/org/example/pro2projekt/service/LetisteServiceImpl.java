package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetadloMapper;
import org.example.pro2projekt.mappaers.LetisteMapper;
import org.example.pro2projekt.objects.Letiste;
import org.example.pro2projekt.repository.LetisteRepository;
import org.example.pro2projekt.validation.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetisteServiceImpl implements LetisteService{
    @Autowired
    private LetisteRepository letisteRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    validator validator = new validator();
    @Override
    public List<Letiste> findAll() {
        String query = "SELECT * FROM Letiste L ";
        return jdbcTemplate.query(query,new LetisteMapper());
    }

    @Override
    public List<Letiste> findByStat(String stat) {
        String query = "SELECT * FROM Letiste L WHERE L.Stat = ?";
        return jdbcTemplate.query(query,new LetisteMapper(),stat);
    }

    @Override
    public List<Letiste> findByIdAndDelete(int id) {
        String query = "DELETE FROM Letiste WHERE Letiste.LetisteID = ?";
        return jdbcTemplate.query(query,new LetisteMapper(),id);
    }

    @Override
    public void findByIdAndUpdate(int id, int kapacita, String mesto, String nazev, String stat) {
        boolean valid = validator.isValidLetiste(mesto,kapacita,nazev,stat);
        if(valid){
            String query = "UPDATE Letiste SET Letiste.Mesto = ?, Letiste.Kapacita = ?, Letiste.Nazev = ?, Letiste.Stat = ? WHERE Letiste.LetisteID = ?";
            jdbcTemplate.query(query,new LetadloMapper(),mesto,kapacita,nazev,stat,id);
        }
    }

    @Override
    public void createLetiste(int kapacita, String mesto, String nazev, String stat) {
        boolean valid = validator.isValidLetiste(mesto,kapacita,nazev,stat);
        if(valid){
            String query = "INSERT INTO  Letiste (Mesto, Kapacita, Nazev, Stat) VALUES (?,?,?,?)";
            jdbcTemplate.query(query,new LetadloMapper(),mesto,kapacita,nazev,stat);
        }
    }
}
