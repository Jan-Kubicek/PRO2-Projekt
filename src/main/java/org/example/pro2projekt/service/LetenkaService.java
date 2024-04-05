package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Letenka;

import java.util.List;

public interface LetenkaService {
    List<Letenka> findByPasazer(int id);
    List<Letenka> findByLet(int id);
    List<Letenka> findByPasazerTrida(int id, String trida);
}
