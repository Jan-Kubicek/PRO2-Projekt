package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.Spolecnost;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpolecnostMapper implements RowMapper<Spolecnost> {
    @Override
    public Spolecnost mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spolecnost spolecnost = new Spolecnost();
        spolecnost.setSpolecnostID(rs.getInt("SPOLECNOSTID"));
        spolecnost.setNazev(rs.getString("NAZEV"));
        spolecnost.setSidlo(rs.getString("SIDLO"));
        return spolecnost;
    }
}
