package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Pasazer;

import java.util.List;

public interface PasazerService {
    List<Pasazer> findAll();

    List<Pasazer> findByJmenoAndPrijmeni(String jmeno, String prijmeni);

    int findByEmailAndPassword(String email, String password);

    Pasazer findByEmail(String email);

    List<Pasazer> findByID(int id);

    List<Pasazer> findByTypPasazeraID(int id);

    List<Pasazer> findByIdAndDelete(int id);

    void createPasazer(String email, String heslo, String jmeno, String prijmeni, String rodneCislo, String tel);

    void findByIdAndUpdate(int id, String jmeno, String prijmeni, String email, String rodneCislo, String tel);
}
