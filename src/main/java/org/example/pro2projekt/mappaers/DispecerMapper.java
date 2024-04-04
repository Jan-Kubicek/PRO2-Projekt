package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.Dispecer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DispecerMapper implements RowMapper<Dispecer> {
    @Override
    public Dispecer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dispecer dispecer = new Dispecer();
        dispecer.setDispecerID(rs.getInt("DISPECERID"));
        dispecer.setEmail(rs.getString("EMAIL"));
        dispecer.setHeslo(rs.getString("HESLO"));
        dispecer.setJmeno(rs.getString("JMENO"));
        dispecer.setPrijmeni(rs.getString("PRIJMENI"));
        dispecer.setRodne_cislo(rs.getString("RODNE_CISLO"));
        dispecer.setTelefoni_cislo(rs.getString("TELEFONI_CISLO"));
        return dispecer;
    }
}
