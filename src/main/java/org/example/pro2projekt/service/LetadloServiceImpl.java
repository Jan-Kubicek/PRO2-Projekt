package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetadloMapper;
import org.example.pro2projekt.mappaers.PasazerMapper;
import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.repository.LetadloRepository;
import org.example.pro2projekt.validation.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetadloServiceImpl implements LetadloService {
    @Autowired
    private LetadloRepository letadloRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private validator validator = new validator();
    @Override
    public List<Letadlo> findAll() {
        String query = "SELECT * FROM Letadlo ";
        return jdbcTemplate.query(query,new LetadloMapper());
    }

    @Override
    public List<Letadlo> findByNazev(String nazev) {
        String query = "SELECT * FROM Letadlo L WHERE L.Nazev = ?";
        return jdbcTemplate.query(query,new LetadloMapper(), nazev);
    }

    @Override
    public List<Letadlo> findByVyrobce(String vyrobce) {
        String query = "SELECT * FROM Letadlo L WHERE L.Vyrobce = ?";
        return jdbcTemplate.query(query,new LetadloMapper(),vyrobce);
    }

    @Override
    public List<Letadlo> findByIdAndDelete(int id) {
        String query = "DELETE FROM Letadlo WHERE Letadlo.LetadloID = ?";
        return jdbcTemplate.query(query,new LetadloMapper(),id);
    }

    @Override
    public void findByIdAndUpdate(int id, String nazev, String rok, String stav, String typ, String vyrobce) {
        boolean valid = validator.isValidLetadlo(nazev,rok,stav,typ,vyrobce);
        if(valid){
            String query = "UPDATE Letadlo SET Letadlo.Nazev = ?, Letadlo.Rok_vyroby = ?, Letadlo.Stav = ?, Letadlo.Typ = ?, Letadlo.Vyrobce = ? WHERE Letadlo.LetadloID = ?";
            jdbcTemplate.query(query,new LetadloMapper(),nazev,rok,stav,typ,vyrobce,id);
        }
    }

    @Override
    public void createNewLetadlo(String nazev, String vyrobce, String typ, String rok, String stav, int spolecnost) {
        boolean valid = validator.isValidLetadlo(nazev,rok,stav,typ,vyrobce);
        if(valid){
            String query = "INSERT INTO  Letadlo (SpolecnostID, Nazev, Rok_vyroby, Stav, Typ, Vyrobce) VALUES (?,?,?,?,?,?)";
            jdbcTemplate.query(query,new LetadloMapper(),spolecnost,nazev,rok,stav,typ,vyrobce);
        }
    }
}
