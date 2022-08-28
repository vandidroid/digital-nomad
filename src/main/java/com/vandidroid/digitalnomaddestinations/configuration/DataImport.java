package com.vandidroid.digitalnomaddestinations.configuration;

import com.vandidroid.digitalnomaddestinations.model.Gender;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.repository.CountryRepository;
import com.vandidroid.digitalnomaddestinations.repository.DigitalNomadRepository;
import com.vandidroid.digitalnomaddestinations.repository.LocationRepository;
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
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private DigitalNomadRepository digitalNomadRepository;

    @Bean
    public CommandLineRunner fillDatabase() {
        return args -> {
            System.out.println("Fill the Database");

            if (countryRepository.count() == 0) {
                ResponseEntity<Country[]> response = restTemplate.getForEntity("https://restcountries.com/v2/all", Country[].class);
                Country[] countries = response.getBody();
                Arrays.stream(countries).forEach(country -> {
                    country = countryRepository.save(country);

                    if (country.getCapital() != null) {
                        locationRepository.save(new Location(country.getCapital(), country));
                    }
                });
            }

            if (digitalNomadRepository.count() == 0) {
                Location location = locationRepository.save(new Location("Miami", countryRepository.findById(240L).get()));
                digitalNomadRepository.save(new DigitalNomad(11L, "1vandidroid1@gmail.com", "Andrea", "Vincze", "Andi", Gender.FEMALE, location));
            }
        };
    }
}
