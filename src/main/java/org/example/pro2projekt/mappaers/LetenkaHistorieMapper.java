package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.LetenkaHistorie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetenkaHistorieMapper implements RowMapper<LetenkaHistorie> {

    @Override
    public LetenkaHistorie mapRow(ResultSet rs, int rowNum) throws SQLException {
        LetenkaHistorie letenkaHistorie = new LetenkaHistorie();
        letenkaHistorie.setLetId(rs.getInt("LETID"));
        letenkaHistorie.setCas_Odletu(rs.getDate("CAS_ODLETU"));
        letenkaHistorie.setLetadloID(rs.getInt("LETADLOID"));
        letenkaHistorie.setMestoOdletu(rs.getString("MESTOODLETU"));
        letenkaHistorie.setMestoPriletu(rs.getString("MESTOPRILETU"));
        letenkaHistorie.setNazevLOdletu(rs.getString("NAZEVLODLETU"));
        letenkaHistorie.setNazevLPriletu(rs.getString("NAZEVLPRILETU"));
        letenkaHistorie.setStatOdletu(rs.getString("STATODLETU"));
        letenkaHistorie.setStatPriletu(rs.getString("STATPRILETU"));
        return null;
    }
}
