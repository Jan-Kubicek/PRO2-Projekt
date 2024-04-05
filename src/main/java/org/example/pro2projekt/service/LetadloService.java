package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Letadlo;

import java.util.List;

public interface LetadloService {
    List<Letadlo> findAll();
    List<Letadlo> findByNazev(String nazev);
    List<Letadlo> findByVyrobce(String vyrobce);
}
