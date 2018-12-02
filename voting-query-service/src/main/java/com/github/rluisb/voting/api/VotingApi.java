package com.github.rluisb.voting.api;

import com.github.rluisb.voting.model.QueryResponse;
import com.github.rluisb.voting.model.Vote;
import com.github.rluisb.voting.model.VoteListResponse;
import com.github.rluisb.voting.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = "Voting Query Api")
public class VotingApi implements BaseVersion {

    @Autowired
    private VoteService voteService;

    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private static final int OK = 200;
    private static final int NOT_FOUND = 417;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final String OK_MESSAGE = "Successfully Operation.";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong in server. Please try again in a few minutes.";
    private static final String NOT_FOUND_MESSAGE = "Cannot send this command. Please, try again later.";

    @GetMapping("/votes")
    @ApiOperation(value = "This endpoint is destinated to view votes")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = VoteListResponse.class),
            @ApiResponse(code = NOT_FOUND, message = NOT_FOUND_MESSAGE, response = QueryResponse.class),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> getAll() {
        LOGGER.info("Beggining votes search...");
        List<Vote> votes = voteService.getAllVotes();
        if (votes.isEmpty()) {
            LOGGER.info("No votes were found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new QueryResponse(1, "No events were found", null));
        }
        LOGGER.info(String.format("Votes found: %s", votes));
        return ok().body(votes);
    }

    @GetMapping("/votes")
    @ApiOperation(value = "This endpoint is destinated to view votes")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = Vote.class),
            @ApiResponse(code = NOT_FOUND, message = NOT_FOUND_MESSAGE, response = QueryResponse.class),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> getByVotingSessionIdAndVoterId(@RequestParam String votingSessionId, @RequestParam String voterId) {
        LOGGER.info("Beggining vote search...");
        Vote vote = voteService.getVoteByVotingSessionIdAndVoterId(votingSessionId, voterId);
        if (Objects.isNull(vote)) {
            LOGGER.info("No votes were found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new QueryResponse(1, "No events were found", null));
        }
        LOGGER.info(String.format("Vote found: %s", vote));
        return ok().body(vote);
    }

    @GetMapping("/votes")
    @ApiOperation(value = "This endpoint is destinated to view votes")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = VoteListResponse.class),
            @ApiResponse(code = NOT_FOUND, message = NOT_FOUND_MESSAGE, response = QueryResponse.class),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> getAllByVotingSessionId(@RequestParam String votingSessionId) {
        LOGGER.info("Beggining vote search...");
        List<Vote> votes = voteService.getAllVotesByVotingSessionId(votingSessionId);
        if (votes.isEmpty()) {
            LOGGER.info("No votes were found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new QueryResponse(1, "No events were found", null));
        }
        LOGGER.info(String.format("Votes found: %s", votes));
        return ok().body(votes);
    }
}