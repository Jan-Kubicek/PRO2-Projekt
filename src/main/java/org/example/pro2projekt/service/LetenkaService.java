package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Letenka;

import java.util.Date;
import java.util.List;

public interface LetenkaService {
    List<Letenka> findByPasazer(int id);
    List<Letenka> findByLet(int id);
    List<Letenka> findByPasazerTrida(int id, String trida);
    List<String> getAllStates();
    List<String> getAllClasses();
    void createNewLetenka(int letId, int pasazerId, int jeSkupinova, int pocet_Pasazeru, String Trida);
}
