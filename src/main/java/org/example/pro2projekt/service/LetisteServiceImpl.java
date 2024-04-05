package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.LetisteMapper;
import org.example.pro2projekt.objects.Letiste;
import org.example.pro2projekt.repository.LetisteRepository;
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

    @Override
    public List<Letiste> findAll() {
        return letisteRepository.findAll();
    }

    @Override
    public List<Letiste> findByStat(String stat) {
        String query = "SELECT * FROM Letiste L WHERE L.Stat = ?";
        return jdbcTemplate.query(query,new LetisteMapper(),stat);
    }
}
