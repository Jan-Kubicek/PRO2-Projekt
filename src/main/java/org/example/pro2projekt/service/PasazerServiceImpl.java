package org.example.pro2projekt.service;

import jakarta.persistence.Access;
import org.example.pro2projekt.mappaers.PasazerMapper;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.repository.PasazerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasazerServiceImpl implements PasazerService{
    @Autowired
    private PasazerRepository pasazerRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pasazer> findAll() {
        System.out.print(pasazerRepository.findAll().size());
        return pasazerRepository.findAll();
    }

    @Override
    public List<Pasazer> findByJmenoAndPrijmeni(String jmeno, String prijmeni) {
        String query = "SELECT * FROM Pasazer P WHERE P.Jmeno = ? AND P.Prijmeni = ?";
        return jdbcTemplate.query(query,new PasazerMapper(), jmeno, prijmeni);
    }

    @Override
    public List<Pasazer> findByID(int id) {
        String query = "SELECT * FROM Pasazer P WHERE P.PasazerID = ?";
        return jdbcTemplate.query(query,new PasazerMapper(), id);
    }

    @Override
    public List<Pasazer> findByTypPasazeraID(int id) {
        String query = "SELECT * FROM Pasazer P WHERE P.Typ_pasazeraID = ?";
        return jdbcTemplate.query(query,new PasazerMapper(),id);
    }
}
