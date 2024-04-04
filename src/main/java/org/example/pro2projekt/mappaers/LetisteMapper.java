package org.example.pro2projekt.mappaers;


import org.example.pro2projekt.objects.Letiste;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetisteMapper implements RowMapper<Letiste> {
    @Override
    public Letiste mapRow(ResultSet rs, int rowNum) throws SQLException {
        Letiste letiste = new Letiste();
        letiste.setLetisteID(rs.getInt("LETISTEID"));
        letiste.setNazev(rs.getString("NAZEV"));
        letiste.setKapacita(rs.getInt("KAPACITA"));
        letiste.setStat(rs.getString("STAT"));
        letiste.setMesto(rs.getString("MESTO"));
        return letiste;
    }
}
