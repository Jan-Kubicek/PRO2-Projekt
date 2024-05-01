package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Zavazadlo;

import java.util.List;

public interface ZavazadloService {
    List<Zavazadlo> findByPasazerId(int id);

    List<Zavazadlo> findByIdAndDelete(int id);

    void findByIdAndUpdate(int id, int sirka, int vyska, int vaha, int krehke, int typ, int hloubka);

    void createZavazadlo(int pasazerId, int sirka, int vyska, int vaha, int krehke, int typ);
}
