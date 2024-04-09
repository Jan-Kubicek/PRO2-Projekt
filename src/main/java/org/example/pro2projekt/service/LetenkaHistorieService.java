package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.LetenkaHistorie;

import java.util.List;

public interface LetenkaHistorieService {
    List<LetenkaHistorie> findByLetID(int letID);
    List<LetenkaHistorie> findAll();
}
