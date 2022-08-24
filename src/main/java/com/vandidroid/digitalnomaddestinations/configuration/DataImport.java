package com.vandidroid.digitalnomaddestinations.configuration;

import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class DataImport {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CountryRepository countryRepository;

    @Bean
    public CommandLineRunner fillDatabase() {
        return args -> {
            System.out.println("Fill the Countries Table");

            if (countryRepository.count() == 0) {
                ResponseEntity<Country[]> response = restTemplate.getForEntity("https://restcountries.com/v2/all", Country[].class);
                Country[] countries = response.getBody();
                Arrays.stream(countries).forEach(country -> countryRepository.save(country));
            }
        };
    }
}
