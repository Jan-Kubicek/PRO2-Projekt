package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.DispecerMapper;
import org.example.pro2projekt.mappaers.LetadloMapper;
import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.repository.DispecerRepository;
import org.example.pro2projekt.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DispecerServiceImpl implements DispecerService{
    @Autowired
    private DispecerRepository dispecerRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Validator validator = new Validator();
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

    @Override
    public List<Dispecer> findByIdAndDelete(int id) {
        String query = "DELETE FROM Dispecer WHERE Dispecer.DispecerID = ?";
        return jdbcTemplate.query(query,new DispecerMapper(),id);
    }

    @Override
    public void findByIdAndUpdate(int id, String jmeno, String prijmeni, String email, String rodneCislo, String tel) {
        boolean valid = validator.isValid(jmeno,prijmeni,email,rodneCislo,tel);
        if(valid){
            String query = "UPDATE Dispecer SET Dispecer.Jmeno = ?, Dispecer.Prijmeni = ?, Dispecer.Email = ?, Dispecer.Rodne_cislo = ?, Dispecer.Telefoni_cislo = ? WHERE Dispecer.DispecerID = ?";
            jdbcTemplate.query(query,new DispecerMapper(),jmeno,prijmeni,email,rodneCislo,tel,id);
        }
    }

    @Override
    public void createDispecer(String email, String heslo, String jmeno, String prijmeni, String rodneCislo, String tel) {
        boolean valid = validator.isValid(jmeno,prijmeni,email,rodneCislo,tel);
        if(valid){
            String hashedHeslo =  BCrypt.hashpw(heslo, BCrypt.gensalt());
            String query = "INSERT INTO  Dispecer (Email, Heslo, Jmeno, Prijmeni, Rodne_cislo, Telefoni_cislo) VALUES (?,?,?,?,?,?)";
            jdbcTemplate.query(query,new DispecerMapper(),email,hashedHeslo,jmeno,prijmeni,rodneCislo,tel);
        }
    }

    @Override
    public Dispecer findByEmail(String email) {
        String query = "SELECT * FROM Dispecer WHERE Dispecer.Email = ?";
        return jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<>(Dispecer.class),email);
    }
}