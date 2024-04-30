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
    private final ZavazadloRepository zavazadloRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ZavazadloServiceImpl(ZavazadloRepository zavazadloRepository, JdbcTemplate jdbcTemplate) {
        this.zavazadloRepository = zavazadloRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Zavazadlo> findByPasazerId(int id) {
        String query = "SELECT * FROM Zavazadlo Z WHERE Z.PasazerID = ?";
        return jdbcTemplate.query(query, new ZavazadloMapper(), id);
    }

    @Override
    public List<Zavazadlo> findByIdAndDelete(int id) {
        String query = "DELETE FROM Zavazadlo WHERE Zavazadlo.ZavazadloID = ?";
        return jdbcTemplate.query(query, new ZavazadloMapper(), id);
    }

    @Override
    public void findByIdAndUpdate(int id, int sirka, int vyska, int vaha, int krehke, int typ) {
        String query = "UPDATE Zavazadlo SET Zavazadlo.Krehke = ?, Zavazadlo.Sirka = ?, Zavazadlo.Vaha = ?, Zavazadlo.Vyska = ?, Zavazadlo.Typ_zavazadlaID = ? WHERE Zavazadlo.ZavazadloID = ?";
        jdbcTemplate.query(query, new ZavazadloMapper(), krehke, sirka, vaha, vyska, typ, id);
    }

    @Override
    public void createZavazadlo(int pasazerID, int sirka, int vyska, int vaha, int krehke, int typ) {
        String query = "INSERT INTO  Zavazadlo (PasazerID,Krehke,Sirka,Vyska,Vaha,Typ_zavazadlaID) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.query(query, new ZavazadloMapper(), pasazerID, krehke, sirka, vyska, vaha, typ);
    }
}
