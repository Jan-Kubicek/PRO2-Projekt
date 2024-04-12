package org.example.pro2projekt.mappaers;

import org.example.pro2projekt.objects.Letenka;
import org.example.pro2projekt.objects.LetenkaRegister;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetenkaRegisterMapper implements RowMapper<LetenkaRegister> {

    @Override
    public LetenkaRegister mapRow(ResultSet rs, int rowNum) throws SQLException {
        LetenkaRegister letenkaRegister = new LetenkaRegister();
        letenkaRegister.setCas_Odletu(rs.getDate("CAS_ODLETU"));
        letenkaRegister.setLetadloID(rs.getInt("LETADLOID"));
        letenkaRegister.setLetId(rs.getInt("LETID"));
        letenkaRegister.setMestoOdletu(rs.getString("MESTOODLETU"));
        letenkaRegister.setMestoPriletu(rs.getString("MESTOPRILETU"));
        letenkaRegister.setCas_Priletu(rs.getDate("CAS_PRILETU"));
        letenkaRegister.setNazevLOdletu(rs.getString("NAZEVLODLETU"));
        letenkaRegister.setNazevLPriletu(rs.getString("NAZEVLPRILETU"));
        letenkaRegister.setStatOdletu(rs.getString("STATODLETU"));
        letenkaRegister.setStatPriletu(rs.getString("STATPRILETU"));
        letenkaRegister.setTrida(rs.getString("TRIDA"));
        return letenkaRegister;
    }
}
