package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.objects.Pasazer;

import java.util.List;

public interface LetadloService {
    List<Letadlo> findAll();
    List<Letadlo> findByNazev(String nazev);
    List<Letadlo> findByVyrobce(String vyrobce);
    List<Letadlo> findByIdAndDelete(int id);
    void findByIdAndUpdate(int id, String nazev, String rok, String stav, String typ, String vyrobce);
    void createNewLetadlo(String nazev, String vyrobce, String typ, String rok, String stav, int spolecnost);
}
