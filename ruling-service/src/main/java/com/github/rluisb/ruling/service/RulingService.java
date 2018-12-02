package com.github.rluisb.ruling.service;

import com.github.rluisb.ruling.entity.Ruling;
import com.github.rluisb.ruling.repository.RulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulingService {

    @Autowired
    private RulingRepository rulingRepository;

    public List<Ruling> getAllRulings() {
        return rulingRepository.findAll();
    }

    public Ruling getRulingById(String id) {
        return rulingRepository.findOne(id);
    }

    public Ruling saveRuling(Ruling ruling) {
        return rulingRepository.save(ruling);
    }

}
