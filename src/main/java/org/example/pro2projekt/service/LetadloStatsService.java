package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.objects.LetadloStats;

import java.util.List;

public interface LetadloStatsService {
    List<LetadloStats> groupByVyrobces();
}
