package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.Zavazadlo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZavazadloMapper implements RowMapper<Zavazadlo> {
    @Override
    public Zavazadlo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Zavazadlo zavazadlo = new Zavazadlo();
        zavazadlo.setZavazadloID(rs.getInt("ZAVAZADLOID"));
        zavazadlo.setKrehke(rs.getInt("KREHKE"));
        zavazadlo.setSirka(rs.getInt("SIRKA"));
        zavazadlo.setVaha(rs.getInt("VAHA"));
        zavazadlo.setVyska(rs.getInt("VYSKA"));
        zavazadlo.setPasazerID(rs.getInt("PASAZERID"));
        zavazadlo.setTyp_zavazadlaID(rs.getInt("TYP_ZAVAZADLAID"));
        zavazadlo.setHloubka(rs.getInt("HLOUBKA"));
        return zavazadlo;
    }
}
