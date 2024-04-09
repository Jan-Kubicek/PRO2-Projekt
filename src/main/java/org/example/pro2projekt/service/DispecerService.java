package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;

import java.util.List;

public interface DispecerService {
    List<Dispecer> findAll();
    List<Dispecer> findByJmenoAPrijmeni(String jmeno, String prijmeni);
    List<Dispecer> findByIdAndDelete(int id);
    void findByIdAndUpdate(int id, String jmeno, String prijmeni, String email, String rodneCislo, String tel);
    void createDispecer(String email, String heslo, String jmeno, String prijmeni, String rodneCislo, String tel);
    Dispecer findByEmail(String email);
}
