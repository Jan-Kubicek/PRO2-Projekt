package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Letiste;

import java.util.List;

public interface LetisteService {
    List<Letiste> findAll();
    List<Letiste> findByStat(String stat);
}
