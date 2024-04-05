package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.DispecerMapper;
import org.example.pro2projekt.mappaers.PasazerMapper;
import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.repository.DispecerRepository;
import org.example.pro2projekt.validation.validator;
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
    private validator validator = new validator();
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
}
