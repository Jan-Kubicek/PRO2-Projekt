package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Pasazer;

import java.util.List;

public interface PasazerService {
    List<Pasazer> findAll();

    List<Pasazer> findByJmenoAndPrijmeni(String jmeno, String prijmeni);

    int findByEmailAndPassword(String email, String password);
    int findIdByEmail(String email);

    Pasazer findByEmail(String email);

    List<Pasazer> findByID(int id);

    List<Pasazer> findByTypPasazeraID(int id);

    List<Pasazer> findByIdAndDelete(int id);

    void findByIdAndUpdate(int id, String jmeno, String prijmeni, String email, String rodneCislo, String tel);

    List<Pasazer> findAllDispecers();

    List<Pasazer> findByJmenoAPrijmeniDispecer(String jmeno, String prijmeni);

    List<Pasazer> findByIdAndDeleteDispecer(int id);

    void findByIdAndUpdateDispecer(int id, String jmeno, String prijmeni, String email, String rodneCislo, String tel);

    void createDispecer(String email, String heslo, String jmeno, String prijmeni, String rodneCislo, String tel);

    Pasazer findByEmailDispecer(String email);
}
