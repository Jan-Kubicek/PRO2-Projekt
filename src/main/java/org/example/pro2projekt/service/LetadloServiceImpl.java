package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetadloMapper;
import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.repository.LetadloRepository;
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
}
