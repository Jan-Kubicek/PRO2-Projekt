package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetadloMapper;
import org.example.pro2projekt.mappaers.PasazerMapper;
import org.example.pro2projekt.mappaers.SpolecnostMapper;
import org.example.pro2projekt.objects.Spolecnost;
import org.example.pro2projekt.repository.SpolecnostRepository;
import org.example.pro2projekt.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpolecnostServiceImpl implements SpolecnostService {
    private final SpolecnostRepository spolecnostRepository;
    private final JdbcTemplate jdbcTemplate;
    Validator validator = new Validator();

    @Autowired
    public SpolecnostServiceImpl(SpolecnostRepository spolecnostRepository, JdbcTemplate jdbcTemplate) {
        this.spolecnostRepository = spolecnostRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Spolecnost> findAll() {
        String query = "SELECT * FROM Spolecnost";
        return jdbcTemplate.query(query, new SpolecnostMapper());
    }

    @Override
    public List<Integer> findAllIndexes() {
        String query = "SELECT SpolecnostID FROM Spolecnost";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getInt("SpolecnostID"));
    }

    @Override
    public List<Spolecnost> finByIdAndDelete(int id) {
        String query = "DELETE FROM Spolecnost WHERE Spolecnost.SpolecnostID = ?";
        return jdbcTemplate.query(query, new SpolecnostMapper(), id);
    }

    @Override
    public void findByIdAndUpdate(int id, String nazev, String sidlo) {
        boolean valid = validator.isValidSpolecnost(nazev, sidlo);
        if (valid) {
            String query = "UPDATE Spolecnost SET Spolecnost.Nazev = ?, Spolecnost.Sidlo = ? WHERE Spolecnost.SpolecnostID = ?";
            jdbcTemplate.query(query, new PasazerMapper(), nazev, sidlo, id);
        }
    }

    @Override
    public void createSpolecnost(String nazev, String sidlo) {
        boolean valid = validator.isValidSpolecnost(nazev, sidlo);
        if (valid) {
            String query = "INSERT INTO  Spolecnost (Nazev, Sidlo) VALUES (?,?)";
            jdbcTemplate.query(query, new LetadloMapper(), nazev, sidlo);
        }
    }
}
