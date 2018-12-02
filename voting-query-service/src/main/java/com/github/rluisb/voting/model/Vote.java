package com.github.rluisb.voting.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vote {

    @Id
    private String id;
    private String voterId;
    private String name;
    private Boolean vote;
    private String votingSessionId;

    public Vote() {
    }

    public Vote(String id, String voterId, String name, Boolean vote, String votingSessionId) {
        this.id = id;
        this.voterId = voterId;
        this.name = name;
        this.vote = vote;
        this.votingSessionId = votingSessionId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }

    public String getVotingSessionId() {
        return votingSessionId;
    }

    public void setVotingSessionId(String votingSessionId) {
        this.votingSessionId = votingSessionId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", voterId='" + voterId + '\'' +
                ", name='" + name + '\'' +
                ", vote=" + vote +
                ", votingSessionId='" + votingSessionId + '\'' +
                '}';
    }
}
