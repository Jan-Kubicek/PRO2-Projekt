package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Letenka;
import org.example.pro2projekt.objects.LetenkaRegister;

import java.util.Date;
import java.util.List;

public interface LetenkaRegisterService {
    List<LetenkaRegister> findByStatesDateNumberClass(String statOdletu, String statPriletum, Date datum, int NumOfMembers, String trida);
    List<LetenkaRegister> findAll();
}
