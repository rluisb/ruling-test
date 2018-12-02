package com.github.rluisb.ruling.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.rluisb.ruling.entity.Ruling;

import java.time.LocalDateTime;

public class VotingSessionDto {
    private Ruling ruling;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime votingBeginingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime votingEndingDate;


    public Ruling getRuling() {
        return ruling;
    }

    public void setRuling(Ruling ruling) {
        this.ruling = ruling;
    }

    public LocalDateTime getVotingBeginingDate() {
        return votingBeginingDate;
    }

    public void setVotingBeginingDate(LocalDateTime votingBeginingDate) {
        this.votingBeginingDate = votingBeginingDate;
    }

    public LocalDateTime getVotingEndingDate() {
        return votingEndingDate;
    }

    public void setVotingEndingDate(LocalDateTime votingEndingDate) {
        this.votingEndingDate = votingEndingDate;
    }
}
