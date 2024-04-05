package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Dispecer;

import java.util.List;

public interface DispecerService {
    List<Dispecer> findAll();
    List<Dispecer> findByJmenoAPrijmeni(String jmeno, String prijmeni);
}
