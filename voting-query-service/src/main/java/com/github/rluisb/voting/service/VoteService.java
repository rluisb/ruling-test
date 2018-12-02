package com.github.rluisb.voting.service;

import com.github.rluisb.voting.model.Vote;
import com.github.rluisb.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public List<Vote> getAllVotesByVotingSessionId(String votingSessionId) {
        return voteRepository.findAllByVotingSessionId(votingSessionId);
    }

    public List<Vote> getVoteByVotingSessionIdAndVoterId(String votingSessionId, String voterId) {
        return voteRepository.findByVotingSessionIdAndVoterId(votingSessionId, voterId);
    }

}
