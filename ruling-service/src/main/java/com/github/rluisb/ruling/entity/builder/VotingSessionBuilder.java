package com.github.rluisb.ruling.entity.builder;

import com.github.rluisb.ruling.entity.Ruling;
import com.github.rluisb.ruling.entity.VotingSession;

import java.time.LocalDateTime;

public class VotingSessionBuilder {

    private VotingSession votingSession;

    public VotingSessionBuilder() {
        this.votingSession = new VotingSession();
    }

    public static VotingSessionBuilder builder() {
        return new VotingSessionBuilder();
    }

    public VotingSessionBuilder withRuling(Ruling ruling) {
        this.votingSession.setRuling(ruling);
        return this;
    }

    public VotingSessionBuilder withVotingBeginningDate(LocalDateTime votingBeginingDate) {
        this.votingSession.setVotingBeginningDate(votingBeginingDate);
        return this;
    }

    public VotingSessionBuilder withVotingEndingDate(LocalDateTime votingEndingDate) {
        this.votingSession.setVotingEndingDate(votingEndingDate);
        return this;
    }

    public VotingSession build(){
        return this.votingSession;
    }
}
