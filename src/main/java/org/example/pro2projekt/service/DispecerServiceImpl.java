package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.DispecerMapper;
import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.repository.DispecerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DispecerServiceImpl implements DispecerService{
    @Autowired
    private DispecerRepository dispecerRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Dispecer> findAll() {
        String query = "SELECT * FROM Dispecer";
        return jdbcTemplate.query(query, new DispecerMapper());
    }

    @Override
    public List<Dispecer> findByJmenoAPrijmeni(String jmeno, String prijmeni) {
        String query = "SELECT * FROM Dispecer D WHERE D.Jmeno = ? AND D.Prijmeni = ?";
        return jdbcTemplate.query(query, new DispecerMapper(), jmeno, prijmeni);
    }
}
