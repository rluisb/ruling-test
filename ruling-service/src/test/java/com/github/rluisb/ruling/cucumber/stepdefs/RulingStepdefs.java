package com.github.rluisb.ruling.cucumber.stepdefs;

import com.github.rluisb.ruling.TestConfig;
import com.github.rluisb.ruling.api.dto.RulingDto;
import com.github.rluisb.ruling.cucumber.World;
import com.github.rluisb.ruling.entity.Ruling;
import com.github.rluisb.ruling.repository.RulingRepository;
import com.github.rluisb.ruling.service.RulingService;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RulingStepdefs extends TestConfig implements En {

    @Autowired
    private World world;
    @LocalServerPort
    private Long port;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RulingService rulingService;

    public RulingStepdefs() {
        Before(new String[]{"@Ruling"}, () -> {
            world.rulingDto = new RulingDto();
            world.ruling = new Ruling();
        });

        Given("^a description (.*)$", (String description) -> {
            world.rulingDto.setDescription(description);
        });
        Given("^a subject (.*)$", (String subject) -> {
            world.rulingDto.setSubject(subject);
        });
        Given("^a title (.*)$", (String title) -> {
            world.rulingDto.setTitle(title);
        });
        Given("^an existing ruling$", () -> {
            world.status = 200;
            ParameterizedTypeReference<List<Ruling>> ptr = new ParameterizedTypeReference<List<Ruling>>(){};
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            try {
                List<Ruling> rulings = restTemplate.exchange(String.format("http://localhost:%s/api/ruling/v1/rulings", port),
                        HttpMethod.GET,
                        entity, ptr).getBody();
                world.ruling = rulings.get(0);
                world.rulingDto.setTitle(world.ruling.getTitle());
                world.rulingDto.setDescription(world.ruling.getDescription());
                world.rulingDto.setSubject(world.ruling.getSubject());
            } catch (HttpServerErrorException | HttpClientErrorException e){
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        Given("^an existing id$", () -> {
            world.status = 200;
            ParameterizedTypeReference<List<Ruling>> ptr = new ParameterizedTypeReference<List<Ruling>>(){};
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            try {
                List<Ruling> rulings = restTemplate.exchange(String.format("http://localhost:%s/api/ruling/v1/rulings", port),
                        HttpMethod.GET,
                        entity, ptr).getBody();
                world.id = rulings.get(0).getId();
            } catch (HttpServerErrorException | HttpClientErrorException e){
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        When("^save this$", () -> {
            world.status = 200;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<RulingDto> entity = new HttpEntity<>(world.rulingDto, headers);

            try {
                world.ruling = restTemplate.exchange(String.format("http://localhost:%s/api/ruling/v1/rulings", port),
                        HttpMethod.POST,
                        entity, Ruling.class).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        When("^update ruling data", () -> {
            world.status = 200;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<RulingDto> entity = new HttpEntity<>(world.rulingDto, headers);

            try {
                world.ruling = restTemplate.exchange(String.format("http://localhost:%s/api/ruling/v1/rulings/%s", port, world.ruling.getId()),
                        HttpMethod.PUT,
                        entity, Ruling.class).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        When("^user request all rulings$", () -> {
            world.status = 200;
            ParameterizedTypeReference<List<Ruling>> ptr = new ParameterizedTypeReference<List<Ruling>>(){};
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            try {
                world.rulings = restTemplate.exchange(String.format("http://localhost:%s/api/ruling/v1/rulings", port),
                        HttpMethod.GET,
                        entity, ptr).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e){
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }

        });
        When("^user request ruling by id$", () -> {
            world.status = 200;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            try {
                Ruling ruling = restTemplate.exchange(String.format("http://localhost:%s/api/ruling/v1/rulings/%s", port, world.id),
                        HttpMethod.GET,
                        entity, Ruling.class).getBody();
                world.ruling = ruling;
            } catch (HttpServerErrorException | HttpClientErrorException e){
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        Then("^it must return the same information given before$", () -> {
            assertEquals(world.rulingDto.getTitle(), world.ruling.getTitle());
            assertEquals(world.rulingDto.getDescription(), world.ruling.getDescription());
            assertEquals(world.rulingDto.getSubject(), world.ruling.getSubject());
        });
        Then("^a new generated id$", () -> {
            assertNotNull(world.ruling.getId());
        });
        Then("^status (\\d+)$", (Integer status) -> {
            assertEquals(status, world.status);
        });
        Then("^it must return a list of rulings$", () -> {
            assertFalse(world.rulings.isEmpty());
        });
        Then("^it must return a valid ruling$", () -> {
            assertNotNull(world.ruling);
            assertEquals(world.ruling.getId(), world.id);
        });
    }
}
