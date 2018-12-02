package com.github.rluisb.voting.service;

import com.github.rluisb.voting.model.Vote;
import com.github.rluisb.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Iterable<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

}
