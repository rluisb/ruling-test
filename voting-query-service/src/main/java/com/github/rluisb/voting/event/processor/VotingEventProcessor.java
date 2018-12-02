package com.github.rluisb.voting.event.processor;

import com.github.rluisb.voting.event.VotingAddedEvent;
import com.github.rluisb.voting.model.Vote;
import com.github.rluisb.voting.repository.VoteRepository;
import org.apache.log4j.Logger;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("amqpEvents")
public class VotingEventProcessor {

    @Autowired
    private VoteRepository voteRepository;

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @EventHandler
    public void on(VotingAddedEvent votingAddedEvent) {
        Vote vote = voteRepository.save(new Vote(votingAddedEvent.getId(), votingAddedEvent.getVote().getVoterId(), votingAddedEvent.getVote().getName(), votingAddedEvent.getVote().getVote(), votingAddedEvent.getVote().getVotingSessionId()));
        LOGGER.info(String.format("A bank account was added! %s", vote));
    }

}
