package com.github.rluisb.ruling.api.v1;

import com.github.rluisb.ruling.api.BaseVersion;
import com.github.rluisb.ruling.api.dto.VotingSessionDto;
import com.github.rluisb.ruling.entity.VotingSession;
import com.github.rluisb.ruling.entity.builder.VotingSessionBuilder;
import com.github.rluisb.ruling.service.VotingSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = "Voting Session Api")
public class VotingSessionApi implements BaseVersion {

    @Autowired
    private VotingSessionService votingSessionService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int OK = 200;
    private static final int NO_CONTENT = 204;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final String OK_MESSAGE = "Successfully Operation.";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong in server. Please try again in a few minutes.";
    private static final String NO_CONTENT_MESSAGE = "Cannot found votingSession to this id";

    @GetMapping(value = "/votingSessions")
    @ApiOperation(value = "This endpoint is destinated to fetch all votingSessions.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = VotingSession.class),
            @ApiResponse(code = NO_CONTENT, message = NO_CONTENT_MESSAGE)
    })
    public ResponseEntity<?> getAllVotingSessions() {
        logger.info("Fetching all votingSessions.");
        List<VotingSession> votingSessions = votingSessionService.getAllVotingSessions();
        logger.info("VotingSessions found");
        return ok().body(votingSessions);
    }

    @GetMapping(value = "/votingSessions/{id}")
    @ApiOperation(value = "This endpoint is destinated to find a votingSession by it id.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = VotingSession.class),
            @ApiResponse(code = NO_CONTENT, message = NO_CONTENT_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> getVotingSessionById(@PathVariable String id) {
        logger.info(String.format("Fetching votingSession by id %s", id));
        VotingSession votingSession = votingSessionService.getVotingSessionById(id);
        logger.info("VotingSession found.");
        return ok().body(votingSession);
    }

    @PostMapping(value = "/votingSessions")
    @ApiOperation(value = "This endpoint is destinated to create a new votingSession.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = VotingSession.class),
            @ApiResponse(code = NO_CONTENT, message = NO_CONTENT_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> saveVotingSession(@Valid @RequestBody VotingSessionDto votingSessionDto) {
        logger.info("Creating new rulling.");
        VotingSession votingSession = votingSessionService.saveVotingSession(buildVotingSession(votingSessionDto));
        logger.info("VotingSession successfully created.");
        return ok().body(votingSession);
    }

    @PutMapping(value = "/votingSessions/{id}")
    @ApiOperation(value = "This endpoint is destinated to update votingSession values.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = VotingSession.class),
            @ApiResponse(code = NO_CONTENT, message = NO_CONTENT_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> updateVotingSession(@PathVariable String id, @Valid @RequestBody VotingSessionDto votingSessionDto) {
        logger.info(String.format("Fetching votingSession by id %s", id));
        VotingSession votingSessionFound = votingSessionService.getVotingSessionById(id);
        if (Objects.isNull(votingSessionFound)) {
            logger.info(String.format("VotingSession not found for id %s", id));
            return notFound().build();
        }
        logger.info(String.format("VotingSession found: %s.", votingSessionFound));
        logger.info("Updating votingSession.");
        VotingSession votingSession = buildVotingSession(votingSessionFound, votingSessionDto);
        logger.info("Saving new votingSession.");
        VotingSession newStateVotingSession = votingSessionService.saveVotingSession(votingSession);
        if (Objects.isNull(newStateVotingSession)) {
            logger.info("Error while the votingSession was in save process.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("New votingSession successfully saved.");
        return ok().body(votingSession);
    }

    private VotingSession buildVotingSession(VotingSessionDto votingSessionDto) {
        return VotingSessionBuilder.builder()
                .withRuling(votingSessionDto.getRuling())
                .withVotingBeginningDate(votingSessionDto.getVotingBeginingDate())
                .withVotingEndingDate(votingSessionDto.getVotingEndingDate())
                .build();
    }

    private VotingSession buildVotingSession(VotingSession votingSession, VotingSessionDto votingSessionDto) {
        votingSession.setRuling(votingSessionDto.getRuling());
        votingSession.setVotingBeginningDate(votingSessionDto.getVotingBeginingDate());
        votingSession.setVotingEndingDate(votingSessionDto.getVotingEndingDate());
        return votingSession;
    }
}

