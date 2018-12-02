package com.github.rluisb.ruling.service;

import com.github.rluisb.ruling.entity.VotingSession;
import com.github.rluisb.ruling.repository.VotingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingSessionService {

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    public List<VotingSession> getAllVotingSessions() {
        return votingSessionRepository.findAll();
    }

    public VotingSession getVotingSessionById(String id) {
        return votingSessionRepository.findOne(id);
    }

    public VotingSession saveVotingSession(VotingSession votingSession) {
        return votingSessionRepository.save(votingSession);
    }
}
