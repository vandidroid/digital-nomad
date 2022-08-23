package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.CountryCommand;
import com.vandidroid.digitalnomaddestinations.model.dto.LocationCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
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
class CountryControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void findAll_returns_with_a_collection_of_countries() {
        ResponseEntity<Country[]> responseEntity = testRestTemplate.getForEntity("/countries", Country[].class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        Country[] countries = responseEntity.getBody();
        assertThat(countries.length).isGreaterThan(0);
        assertThat(countries[0].getName()).isEqualTo("Hungary");
    }

    @Test
    @Order(2)
    void findById_returns_the_correct_country() {
        ResponseEntity<Country> responseEntity = testRestTemplate.getForEntity("/countries/1", Country.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        Country country = responseEntity.getBody();
        assertThat(country.getName()).isEqualTo("Hungary");
    }

    @Test
    @Order(3)
    void findLocationsByCountryId_returns_all_locations_from_a_given_country() {
        ResponseEntity<Location[]> responseEntity = testRestTemplate.getForEntity("/countries/1/locations", Location[].class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        Location[] locations = responseEntity.getBody();
        assertThat(locations.length).isGreaterThan(0);
    }

    @Test
    @Order(4)
    void findDigitalNomadsByCountryId_returns_all_nomads_from_a_given_country() {;
        ResponseEntity<DigitalNomad[]> responseEntity = testRestTemplate.getForEntity("/countries/2/nomads", DigitalNomad[].class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.hasBody()).isTrue();
        DigitalNomad[] digitalNomads = responseEntity.getBody();
        assertThat(digitalNomads.length).isGreaterThan(0);
    }

    @Test
    @Order(5)
    void add_creates_inserts_a_country() {
        CountryCommand countryCommand = new CountryCommand("Thailand");
        HttpEntity<CountryCommand> httpEntity = new HttpEntity<>(countryCommand);
        ResponseEntity<Country> responseEntity = testRestTemplate.postForEntity("/countries", httpEntity, Country.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        Country country = responseEntity.getBody();
        assertThat(country.getId()).isNotNull();
        assertThat(country.getName()).isEqualTo(countryCommand.getName());
    }

    @Test
    @Order(6)
    void update() {
        CountryCommand countryCommand = new CountryCommand("Argentina");
        HttpEntity<CountryCommand> httpEntity = new HttpEntity<>(countryCommand);

        ResponseEntity<Country> responseEntity = testRestTemplate.exchange("/countries/3", HttpMethod.PUT, httpEntity, Country.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Country country = responseEntity.getBody();
        assertThat(country.getId()).isEqualTo(3);
        assertThat(country.getName()).isEqualTo(countryCommand.getName());
    }

    @Test
    @Order(7)
    void deleteById_works_as_expected() {
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/countries/4", HttpMethod.DELETE, null, Void.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(responseEntity.hasBody()).isFalse();
    }
}