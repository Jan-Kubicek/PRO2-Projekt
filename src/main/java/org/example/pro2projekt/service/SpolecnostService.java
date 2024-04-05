package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Spolecnost;

import java.util.List;

public interface SpolecnostService {
    List<Spolecnost> findAll();
    List<Integer> findAllIndexes();
    List<Spolecnost> finByIdAndDelete(int id);
    void findByIdAndUpdate(int id, String nazev, String sidlo);
    void createSpolecnost(String nazev, String sidlo);
}
