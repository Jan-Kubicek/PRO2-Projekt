package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.PasazerMapper;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.repository.PasazerRepository;
import org.example.pro2projekt.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasazerServiceImpl implements PasazerService {
    @Autowired
    private PasazerRepository pasazerRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //@Autowired
    //private PasswordEncoder passwordEncoder;
    private Validator validator = new Validator();

    @Override
    public List<Pasazer> findAll() {
        String query = "SELECT * FROM Pasazer ";
        return jdbcTemplate.query(query, new PasazerMapper());
    }

    @Override
    public List<Pasazer> findByJmenoAndPrijmeni(String jmeno, String prijmeni) {
        String query = "SELECT * FROM Pasazer P WHERE P.Jmeno = ? AND P.Prijmeni = ?";
        return jdbcTemplate.query(query, new PasazerMapper(), jmeno, prijmeni);
    }

    @Override
    public int findByEmailAndPassword(String email, String password) {
        String query = "SELECT P.PasazerID FROM Pasazer P WHERE P.Email = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, email);
    }

    @Override
    public Pasazer findByEmail(String email) {
        String query = "SELECT * FROM Pasazer P WHERE P.Email = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Pasazer.class), email);
    }

    @Override
    public List<Pasazer> findByID(int id) {
        String query = "SELECT * FROM Pasazer WHERE Pasazer.PasazerID = ?";
        return jdbcTemplate.query(query, new PasazerMapper(), id);
    }

    @Override
    public List<Pasazer> findByTypPasazeraID(int id) {
        String query = "SELECT * FROM Pasazer P WHERE P.Typ_pasazeraID = ?";
        return jdbcTemplate.query(query, new PasazerMapper(), id);
    }

    @Override
    public List<Pasazer> findByIdAndDelete(int id) {
        String query = "DELETE FROM Pasazer WHERE Pasazer.PasazerID = ?";
        return jdbcTemplate.query(query, new PasazerMapper(), id);
    }

    @Override
    public void createPasazer(String email, String heslo, String jmeno, String prijmeni, String rodneCislo, String tel) {

    }

    @Override
    public void findByIdAndUpdate(int id, String jmeno, String prijmeni, String email, String rodneCislo, String tel) {
        boolean valid = validator.isValid(jmeno, prijmeni, email, rodneCislo, tel);
        if (valid) {
            String query = "UPDATE Pasazer SET Pasazer.Jmeno = ?, Pasazer.Prijmeni = ?, Pasazer.Email = ?, Pasazer.Rodne_cislo = ?, Pasazer.Telefoni_cislo = ? WHERE Pasazer.PasazerID = ?";
            jdbcTemplate.query(query, new PasazerMapper(), jmeno, prijmeni, email, rodneCislo, tel, id);
        }
    }
}
