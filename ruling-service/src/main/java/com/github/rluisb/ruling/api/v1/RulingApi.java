package com.github.rluisb.ruling.api.v1;

import com.github.rluisb.ruling.api.BaseVersion;
import com.github.rluisb.ruling.api.dto.RulingDto;
import com.github.rluisb.ruling.entity.Ruling;
import com.github.rluisb.ruling.entity.builder.RulingBuilder;
import com.github.rluisb.ruling.service.RulingService;
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
@Api(tags = "Ruling Api")
public class RulingApi implements BaseVersion {

    @Autowired
    private RulingService rulingService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int OK = 200;
    private static final int NO_CONTENT = 204;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final String OK_MESSAGE = "Successfully Operation.";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong in server. Please try again in a few minutes.";
    private static final String NO_CONTENT_MESSAGE = "Cannot found ruling to this id";

    @GetMapping(value = "/rulings")
    @ApiOperation(value = "This endpoint is destinated to fetch all rulings.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = Ruling.class),
            @ApiResponse(code = NO_CONTENT, message = NO_CONTENT_MESSAGE)
    })
    public ResponseEntity<?> getAllRulings() {
        logger.info("Fetching all rulings.");
        List<Ruling> rulings = rulingService.getAllRulings();
        logger.info("Rulings found");
        return ok().body(rulings);
    }

    @GetMapping(value = "/rulings/{id}")
    @ApiOperation(value = "This endpoint is destinated to find a ruling by it id.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = Ruling.class),
            @ApiResponse(code = NO_CONTENT, message = NO_CONTENT_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> getRulingById(@PathVariable String id) {
        logger.info(String.format("Fetching ruling by id %s", id));
        Ruling ruling = rulingService.getRulingById(id);
        logger.info("Ruling found.");
        return ok().body(ruling);
    }

    @PostMapping(value = "/rulings")
    @ApiOperation(value = "This endpoint is destinated to create a new ruling.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = Ruling.class),
            @ApiResponse(code = NO_CONTENT, message = NO_CONTENT_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> saveRuling(@Valid @RequestBody RulingDto rulingDto) {
        logger.info("Creating new rulling.");
        Ruling ruling = rulingService.saveRuling(buildRuling(rulingDto));
        logger.info("Ruling successfully created.");
        return ok().body(ruling);
    }

    @PutMapping(value = "/rulings/{id}")
    @ApiOperation(value = "This endpoint is destinated to update ruling values.")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = Ruling.class),
            @ApiResponse(code = NO_CONTENT, message = NO_CONTENT_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)
    })
    public ResponseEntity<?> updateRuling(@PathVariable String id, @Valid @RequestBody RulingDto rulingDto) {
        logger.info(String.format("Fetching ruling by id %s", id));
        Ruling rulingFound = rulingService.getRulingById(id);
        if (Objects.isNull(rulingFound)) {
            logger.info(String.format("Ruling not found for id %s", id));
            return notFound().build();
        }
        logger.info(String.format("Ruling found: %s.", rulingFound));
        logger.info("Updating ruling.");
        Ruling ruling = buildRuling(rulingFound, rulingDto);
        logger.info("Saving new ruling.");
        Ruling newStateRuling = rulingService.saveRuling(ruling);
        if (Objects.isNull(newStateRuling)) {
            logger.info("Error while the ruling was in save process.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("New ruling successfully saved.");
        return ok().body(ruling);
    }

    private Ruling buildRuling(RulingDto rulingDto) {
        return RulingBuilder.builder()
                .withDescription(rulingDto.getDescription())
                .withTitle(rulingDto.getTitle())
                .withSubject(rulingDto.getSubject())
                .build();
    }

    private Ruling buildRuling(Ruling ruling, RulingDto rulingDto) {
        ruling.setSubject(rulingDto.getSubject());
        ruling.setDescription(rulingDto.getDescription());
        ruling.setTitle(rulingDto.getTitle());
        return ruling;
    }
}

