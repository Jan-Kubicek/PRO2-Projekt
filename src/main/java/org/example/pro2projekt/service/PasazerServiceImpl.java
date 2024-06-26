package org.example.pro2projekt.service;

import org.example.pro2projekt.mappaers.PasazerMapper;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.repository.PasazerRepository;
import org.example.pro2projekt.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasazerServiceImpl implements PasazerService {
    private final PasazerRepository pasazerRepository;
    private final JdbcTemplate jdbcTemplate;
    private final Validator validator = new Validator();

    @Autowired
    public PasazerServiceImpl(PasazerRepository pasazerRepository, JdbcTemplate jdbcTemplate) {
        this.pasazerRepository = pasazerRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pasazer> findAll() {
        String query = "SELECT * FROM Pasazer WHERE Pasazer.Typ_pasazeraID != 6 ";
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
        try {
            return jdbcTemplate.queryForObject(query, Integer.class, email);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int findIdByEmail(String email) {
        String query = "SELECT P.PasazerID FROM Pasazer P WHERE P.Email = ?";
        try {
            return jdbcTemplate.queryForObject(query, Integer.class, email);
        } catch (Exception e) {
            return 0;
        }
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
    public void findByIdAndUpdate(int id, String jmeno, String prijmeni, String email, String rodneCislo, String tel, String datumNarozeni) {
        boolean valid = validator.isValid(jmeno, prijmeni, email, rodneCislo, tel);
        if (valid) {
            String query = "UPDATE Pasazer SET Pasazer.Jmeno = ?, Pasazer.Prijmeni = ?, Pasazer.Email = ?, Pasazer.Rodne_cislo = ?, Pasazer.Telefoni_cislo = ?, Pasazer.Datum_narozeni = ? WHERE Pasazer.PasazerID = ?";
            jdbcTemplate.query(query, new PasazerMapper(), jmeno, prijmeni, email, rodneCislo, tel, datumNarozeni, id);
        }
    }

    @Override
    public List<Pasazer> findAllDispecers() {
        String query = "SELECT * FROM Pasazer WHERE Pasazer.Typ_pasazeraID = 6";
        return jdbcTemplate.query(query, new PasazerMapper());
    }

    @Override
    public List<Pasazer> findByJmenoAPrijmeniDispecer(String jmeno, String prijmeni) {
        String query = "SELECT * FROM Pasazer P WHERE P.Jmeno = ? AND P.Prijmeni = ? AND P.Typ_pasazeraID = 6";
        return jdbcTemplate.query(query, new PasazerMapper(), jmeno, prijmeni);
    }

    @Override
    public List<Pasazer> findByIdAndDeleteDispecer(int id) {
        String query = "DELETE FROM Pasazer WHERE Pasazer.PasazerID = ? AND Pasazer.Typ_pasazeraID = 6";
        return jdbcTemplate.query(query, new PasazerMapper(), id);
    }

    @Override
    public void findByIdAndUpdateDispecer(int id, String jmeno, String prijmeni, String email, String rodneCislo, String tel, String datumNarozeni) {
        boolean valid = validator.isValid(jmeno, prijmeni, email, rodneCislo, tel);
        if (valid) {
            String query = "UPDATE Pasazer SET Pasazer.Jmeno = ?, Pasazer.Prijmeni = ?, Pasazer.Email = ?, Pasazer.Rodne_cislo = ?, Pasazer.Telefoni_cislo = ?, Pasazer.Datum_narozeni = ? WHERE Pasazer.PasazerID = ? AND Pasazer.Typ_pasazeraID = 6";
            jdbcTemplate.query(query, new PasazerMapper(), jmeno, prijmeni, email, rodneCislo, tel, datumNarozeni, id);
        }
    }

    @Override
    public void createDispecer(String email, String heslo, String jmeno, String prijmeni, String rodneCislo, String tel, String datumNarozeni) {
        boolean valid = validator.isValid(jmeno, prijmeni, email, rodneCislo, tel);
        if (valid) {
            String hashedHeslo = BCrypt.hashpw(heslo, BCrypt.gensalt());
            String query = "INSERT INTO  Pasazer (Email, Heslo, Jmeno, Prijmeni, Rodne_cislo, Telefoni_cislo, Datum_narozeni, Typ_pasazeraID) VALUES (?,?,?,?,?,?,?,6)";
            jdbcTemplate.query(query, new PasazerMapper(), email, hashedHeslo, jmeno, prijmeni, rodneCislo, tel, datumNarozeni);
        }
    }

    @Override
    public void createPasazer(String jmeno, String prijmeni, String email, String rodCi, String telCis, String heslo, String pohlavi) {
        boolean valid = validator.isValid(jmeno, prijmeni, email, rodCi, telCis);
        if (valid) {
            String hashedPassword = BCrypt.hashpw(heslo, BCrypt.gensalt());
            int poh = 1; // žena
            if (pohlavi.equals("Muž"))
                poh = 0;
            String query = "INSERT INTO Pasazer ( Datum_narozeni, Email, Heslo, Jmeno, Pohlavi, Prijmeni, Rodne_cislo, Telefoni_cislo,Typ_pasazeraID) VALUES (?,?,?,?,?,?,?,?,1)";
            jdbcTemplate.query(query, new PasazerMapper(), "2024-04-03", email, hashedPassword, jmeno, poh, prijmeni, rodCi, telCis);
        }
    }

    @Override
    public Pasazer findByEmailDispecer(String email) {
        String query = "SELECT * FROM Pasazer P WHERE P.Email = ? AND P.Typ_pasazeraID = 6";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Pasazer.class), email);
    }
}
