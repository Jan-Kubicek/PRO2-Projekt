package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Pasazer;

import java.util.List;

public interface PasazerService {
    List<Pasazer> findAll();
    List<Pasazer> findByJmenoAndPrijmeni(String jmeno, String prijmeni);
    List<Pasazer> findByID(int id);
    List<Pasazer> findByTypPasazeraID(int id);
    List<Pasazer> findByIdAndDelete(int id);
}
