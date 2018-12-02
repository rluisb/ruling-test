package com.github.rluisb.ruling.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "voting-sessions")
public class VotingSession {

    @Id
    private String id;
    private Ruling ruling;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime votingBeginningDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime votingEndingDate;

    public Ruling getRuling() {
        return ruling;
    }

    public void setRuling(Ruling ruling) {
        this.ruling = ruling;
    }

    public LocalDateTime getVotingBeginningDate() {
        return votingBeginningDate;
    }

    public void setVotingBeginningDate(LocalDateTime votingBeginningDate) {
        this.votingBeginningDate = votingBeginningDate;
    }

    public LocalDateTime getVotingEndingDate() {
        return votingEndingDate;
    }

    public void setVotingEndingDate(LocalDateTime votingEndingDate) {
        this.votingEndingDate = votingEndingDate;
    }

    @Override
    public String toString() {
        return "VotingSession{" +
                "id='" + id + '\'' +
                ", ruling=" + ruling +
                ", votingBeginningDate=" + votingBeginningDate +
                ", votingEndingDate=" + votingEndingDate +
                '}';
    }
}
