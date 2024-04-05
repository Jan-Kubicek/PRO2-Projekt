package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Zavazadlo;

import java.util.List;

public interface ZavazadloService {
    List<Zavazadlo> findByPasazerId(int id);
}
