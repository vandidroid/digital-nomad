package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.DigitalNomadCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DigitalNomadControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void findAll() {
        ResponseEntity<DigitalNomad[]> responseEntity = testRestTemplate.getForEntity("/nomads", DigitalNomad[].class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        DigitalNomad[] digitalNomads = responseEntity.getBody();
        assertThat(digitalNomads.length).isGreaterThan(0);
        assertThat(digitalNomads[0].getEmail()).isNotBlank();
    }

    @Test
    @Order(2)
    void findById() {
        ResponseEntity<DigitalNomad> responseEntity = testRestTemplate.getForEntity("/nomads/11", DigitalNomad.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        DigitalNomad digitalNomad = responseEntity.getBody();
        assertThat(digitalNomad.getId()).isEqualTo(11);
        assertThat(digitalNomad.getEmail()).isNotBlank();
    }

    @Test
    @Order(3)
    void add() {
        DigitalNomadCommand digitalNomadCommand = new DigitalNomadCommand();
        digitalNomadCommand.setEmail("john.doe@gmail.com");
        digitalNomadCommand.setNickname("Joe");
        digitalNomadCommand.setLocationId(1L);
        HttpEntity<DigitalNomadCommand> httpEntity = new HttpEntity<>(digitalNomadCommand);

        ResponseEntity<DigitalNomad> responseEntity = testRestTemplate.postForEntity("/nomads", httpEntity, DigitalNomad.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        DigitalNomad digitalNomad = responseEntity.getBody();
        assertThat(digitalNomad.getId()).isNotNull();
        assertThat(digitalNomad.getEmail()).isEqualTo(digitalNomadCommand.getEmail());
        assertThat(digitalNomad.getNickname()).isEqualTo(digitalNomadCommand.getNickname());
    }

    @Test
    @Order(4)
    void update() {
        DigitalNomadCommand digitalNomadCommand = new DigitalNomadCommand();
        digitalNomadCommand.setEmail("unknown@gmail.com");
        digitalNomadCommand.setNickname("Unknown");
        digitalNomadCommand.setLocationId(2L);
        HttpEntity<DigitalNomadCommand> httpEntity = new HttpEntity<>(digitalNomadCommand);

        ResponseEntity<DigitalNomad> responseEntity = testRestTemplate.exchange("/nomads/1", HttpMethod.PUT, httpEntity, DigitalNomad.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        DigitalNomad digitalNomad = responseEntity.getBody();
        assertThat(digitalNomad.getId()).isEqualTo(1);
        assertThat(digitalNomad.getEmail()).isEqualTo(digitalNomadCommand.getEmail());
        assertThat(digitalNomad.getNickname()).isEqualTo(digitalNomadCommand.getNickname());
        assertThat(digitalNomad.getLocation().getId()).isEqualTo(digitalNomadCommand.getLocationId());
    }

    @Test
    @Order(5)
    void relocate() {
        ResponseEntity<DigitalNomad> responseEntity = testRestTemplate.exchange("/nomads/1/relocate/2", HttpMethod.PUT, null, DigitalNomad.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        DigitalNomad digitalNomad = responseEntity.getBody();
        assertThat(digitalNomad.getId()).isEqualTo(1);
        assertThat(digitalNomad.getLocation().getId()).isEqualTo(2);
    }

    @Test
    @Order(6)
    void deleteById() {
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/nomads/1", HttpMethod.DELETE, null, Void.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(responseEntity.hasBody()).isFalse();
    }
}