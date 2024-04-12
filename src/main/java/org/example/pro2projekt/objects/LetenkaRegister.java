package org.example.pro2projekt.objects;

import java.sql.Date;

public class LetenkaRegister {
    private int LetId;
    private int LetadloID;
    private Date Cas_Odletu;
    private Date Cas_Priletu;
    private String MestoOdletu;
    private String StatOdletu;
    private String NazevLOdletu;
    private String MestoPriletu;
    private String StatPriletu;
    private String NazevLPriletu;

    public LetenkaRegister() {
    }

    public LetenkaRegister(int letId, int letadloID, Date cas_Odletu, Date cas_Priletu, String mestoOdletu, String statOdletu, String nazevLOdletu, String mestoPriletu, String statPriletu, String nazevLPriletu) {
        LetId = letId;
        LetadloID = letadloID;
        Cas_Odletu = cas_Odletu;
        Cas_Priletu = cas_Priletu;
        MestoOdletu = mestoOdletu;
        StatOdletu = statOdletu;
        NazevLOdletu = nazevLOdletu;
        MestoPriletu = mestoPriletu;
        StatPriletu = statPriletu;
        NazevLPriletu = nazevLPriletu;
    }

    public int getLetId() {
        return LetId;
    }

    public void setLetId(int letId) {
        LetId = letId;
    }

    public int getLetadloID() {
        return LetadloID;
    }

    public void setLetadloID(int letadloID) {
        LetadloID = letadloID;
    }

    public Date getCas_Odletu() {
        return Cas_Odletu;
    }

    public void setCas_Odletu(Date cas_Odletu) {
        Cas_Odletu = cas_Odletu;
    }

    public Date getCas_Priletu() {
        return Cas_Priletu;
    }

    public void setCas_Priletu(Date cas_Priletu) {
        Cas_Priletu = cas_Priletu;
    }

    public String getMestoOdletu() {
        return MestoOdletu;
    }

    public void setMestoOdletu(String mestoOdletu) {
        MestoOdletu = mestoOdletu;
    }

    public String getStatOdletu() {
        return StatOdletu;
    }

    public void setStatOdletu(String statOdletu) {
        StatOdletu = statOdletu;
    }

    public String getNazevLOdletu() {
        return NazevLOdletu;
    }

    public void setNazevLOdletu(String nazevLOdletu) {
        NazevLOdletu = nazevLOdletu;
    }

    public String getMestoPriletu() {
        return MestoPriletu;
    }

    public void setMestoPriletu(String mestoPriletu) {
        MestoPriletu = mestoPriletu;
    }

    public String getStatPriletu() {
        return StatPriletu;
    }

    public void setStatPriletu(String statPriletu) {
        StatPriletu = statPriletu;
    }

    public String getNazevLPriletu() {
        return NazevLPriletu;
    }

    public void setNazevLPriletu(String nazevLPriletu) {
        NazevLPriletu = nazevLPriletu;
    }
}
