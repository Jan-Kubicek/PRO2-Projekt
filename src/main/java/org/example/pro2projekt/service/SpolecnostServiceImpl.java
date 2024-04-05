package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.SpolecnostMapper;
import org.example.pro2projekt.objects.Spolecnost;
import org.example.pro2projekt.repository.SpolecnostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpolecnostServiceImpl implements  SpolecnostService {
    @Autowired
    private SpolecnostRepository spolecnostRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Spolecnost> findAll() {
        String query = "SELECT * FROM Spolecnost";
        return jdbcTemplate.query(query,new SpolecnostMapper());
    }

    @Override
    public List<Integer> findAllIndexes() {
        String query = "SELECT SpolecnostID FROM Spolecnost";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getInt("SpolecnostID"));
    }
}
