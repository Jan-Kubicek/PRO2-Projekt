package org.example.pro2projekt.objects;

import java.util.Date;

public class LetenkaHistorie {
    private int LetId;
    private int LetadloID;
    private Date Cas_Odletu;
    private String MestoOdletu;
    private String StatOdletu;
    private String NazevLOdletu;
    private String MestoPriletu;
    private String StatPriletu;
    private String NazevLPriletu;

    public LetenkaHistorie() {
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
