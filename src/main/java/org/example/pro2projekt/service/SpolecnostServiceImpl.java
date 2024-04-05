package org.example.pro2projekt.service;

import org.example.pro2projekt.objects.Spolecnost;
import org.example.pro2projekt.repository.SpolecnostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpolecnostServiceImpl implements  SpolecnostService {
    @Autowired
    private SpolecnostRepository spolecnostRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Spolecnost> findAll() {
        return spolecnostRepository.findAll();
    }
}
