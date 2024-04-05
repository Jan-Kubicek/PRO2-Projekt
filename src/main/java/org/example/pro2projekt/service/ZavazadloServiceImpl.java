package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.ZavazadloMapper;
import org.example.pro2projekt.objects.Zavazadlo;
import org.example.pro2projekt.repository.ZavazadloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZavazadloServiceImpl implements ZavazadloService {
    @Autowired
    private ZavazadloRepository zavazadloRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Zavazadlo> findByPasazerId(int id) {
        String query = "SELECT * FROM Zavazadlo Z WHERE Z.PasazerID = ?";
        return jdbcTemplate.query(query, new ZavazadloMapper(),id);
    }
}
