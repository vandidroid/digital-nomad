package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.LocationCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocationControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void findAll() {
        ResponseEntity<Location[]> responseEntity = testRestTemplate.getForEntity("/locations", Location[].class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        Location[] locations = responseEntity.getBody();
        assertThat(locations.length).isGreaterThan(0);
    }

    @Test
    @Order(2)
    void findById() {
        ResponseEntity<Location> responseEntity = testRestTemplate.getForEntity("/locations/1", Location.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        Location location = responseEntity.getBody();
        assertThat(location.getId()).isEqualTo(1);
        assertThat(location.getName()).isEqualTo("Budapest");
    }

    @Test
    @Order(3)
    void findNomadsByLocationId() {
        ResponseEntity<DigitalNomad[]> responseEntity = testRestTemplate.getForEntity("/locations/2/nomads", DigitalNomad[].class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        DigitalNomad[] digitalNomads = responseEntity.getBody();
        assertThat(digitalNomads.length).isEqualTo(1);
        assertThat(digitalNomads[0].getEmail()).isEqualTo("vandidroid@gmail.com");
    }

    @Test
    @Order(4)
    void add() {
        LocationCommand locationCommand = new LocationCommand("Kyoto", 2L);
        HttpEntity<LocationCommand> httpEntity = new HttpEntity<>(locationCommand);

        ResponseEntity<Location> responseEntity = testRestTemplate.postForEntity("/locations", httpEntity, Location.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.hasBody()).isTrue();
        Location location = responseEntity.getBody();
        assertThat(location.getId()).isNotNull();
        assertThat(location.getName()).isEqualTo(locationCommand.getName());
        assertThat(location.getCountry().getName()).isEqualTo("Japan");
    }

    @Test
    @Order(5)
    void update() {
        LocationCommand locationCommand = new LocationCommand("Zalaegerszeg", 1L);
        HttpEntity<LocationCommand> httpEntity = new HttpEntity<>(locationCommand);

        ResponseEntity<Location> responseEntity = testRestTemplate.exchange("/locations/1", HttpMethod.PUT, httpEntity, Location.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        Location location = responseEntity.getBody();
        assertThat(location.getId()).isEqualTo(1);
        assertThat(location.getName()).isEqualTo(locationCommand.getName());
        assertThat(location.getCountry().getId()).isEqualTo(locationCommand.getCountryId());
    }

    @Test
    @Order(6)
    void deleteById() {
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/locations/3", HttpMethod.DELETE, null, Void.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(responseEntity.hasBody()).isFalse();
    }
}