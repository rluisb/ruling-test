package com.github.rluisb.voting.repository;

import com.github.rluisb.voting.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, String> {
    List<Vote> findAll();
    List<Vote> findAllByVotingSessionId(String votingSessionId);
    List<Vote> findByVotingSessionIdAndVoterId(String votingSessionId, String voterId);
}
