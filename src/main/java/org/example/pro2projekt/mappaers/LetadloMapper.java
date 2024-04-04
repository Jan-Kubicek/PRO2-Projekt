package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.objects.Pasazer;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LetadloMapper implements RowMapper<Letadlo> {

    @Override
    public Letadlo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Letadlo letadlo = new Letadlo();
        letadlo.setLetadloID(rs.getInt("LETADLOID"));
        letadlo.setNazev(rs.getString("NAZEV"));
        letadlo.setStav(rs.getString("STAV"));
        letadlo.setVyrobce(rs.getString("VYROBCE"));
        letadlo.setRok_vyroby(rs.getString("ROK_VYROBY"));
        letadlo.setSpolecnostID(rs.getInt("SPOLECNOSTID"));
        letadlo.setTyp(rs.getString("TYP"));
        return letadlo;
    }
}
