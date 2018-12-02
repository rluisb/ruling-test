package com.github.rluisb.voting.model;

import java.util.List;

public class VoteListResponse {

    private List<Vote> votes;

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "VoteListResponse{" +
                "votes=" + votes +
                '}';
    }
}
