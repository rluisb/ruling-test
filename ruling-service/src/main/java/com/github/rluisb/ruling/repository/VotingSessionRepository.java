package com.github.rluisb.ruling.repository;

import com.github.rluisb.ruling.entity.VotingSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSessionRepository  extends MongoRepository<VotingSession, String>{
}
