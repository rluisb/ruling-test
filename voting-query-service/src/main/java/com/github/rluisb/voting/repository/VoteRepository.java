package com.github.rluisb.voting.repository;

import com.github.rluisb.voting.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, String> {
}
