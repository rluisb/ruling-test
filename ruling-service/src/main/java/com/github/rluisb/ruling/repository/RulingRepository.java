package com.github.rluisb.ruling.repository;

import com.github.rluisb.ruling.entity.Ruling;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulingRepository extends MongoRepository<Ruling, String> {
}

