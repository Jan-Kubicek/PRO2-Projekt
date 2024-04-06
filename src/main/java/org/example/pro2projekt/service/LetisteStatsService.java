package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.LetadloStats;
import org.example.pro2projekt.objects.LetisteStats;

import java.util.List;

public interface LetisteStatsService {
    List<LetisteStats> groupByStates();
}
