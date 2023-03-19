package com.vandidroid.digitalnomaddestinations.configuration;

import com.vandidroid.digitalnomaddestinations.model.Gender;
import com.vandidroid.digitalnomaddestinations.model.dto.UnsplashCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.model.entity.User;
import com.vandidroid.digitalnomaddestinations.repository.CountryRepository;
import com.vandidroid.digitalnomaddestinations.repository.DigitalNomadRepository;
import com.vandidroid.digitalnomaddestinations.repository.LocationRepository;
import com.vandidroid.digitalnomaddestinations.repository.UserRepository;
import com.vandidroid.digitalnomaddestinations.service.UnsplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private UnsplashService unsplashService;
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public CommandLineRunner importCountriesFromRestCountries() {
        return args -> {
            System.out.println("Import countries from RestCountries");

            long countryCount = countryRepository.count();

            if (countryCount == 0) {
                ResponseEntity<Country[]> response = restTemplate.getForEntity("https://restcountries.com/v2/all", Country[].class);
                Country[] countries = response.getBody();
                Arrays.stream(countries).forEach(country -> {
                    countryRepository.save(country);
                });
            } else {
                System.out.println(countryCount + " countries are already in database");
            }
        };
    }

    @Bean
    public CommandLineRunner fillLocationsWithCapitals() {
        return args -> {
            System.out.println("Fill the location table with the capitals");
            long locationCount = locationRepository.count();
            if (locationCount == 0) {
                for (Country country : countryRepository.findAll()) {
                    if (country.getCapital() != null) {
                        locationRepository.save(new Location(country.getCapital(), country));
                    }
                }
            } else {
                System.out.println(locationCount + " locations are already in database");
            }
        };
    }

    @Bean
    public CommandLineRunner importImagesFromUnsplash() {
        return args -> {
            System.out.println("Import images from Unsplash");
            long imageCount = unsplashService.count();

            if (imageCount == 0) {
                for (Location location : locationRepository.findAll()) {
                    String data = unsplashService.search(location.getName());
                    unsplashService.add(new UnsplashCommand(data, location.getId()));

                    Long timeout = (long) (Math.random() * 30) + 60;
                    TimeUnit.SECONDS.sleep(timeout);
                }
            } else {
                System.out.println(imageCount + " images are already in database");
            }
        };
    }

    @Bean
    public CommandLineRunner insertDigitalNomad() {
        return args -> {
            System.out.println("Insert digital nomad");
            long diNoCount = digitalNomadRepository.count();

            if (diNoCount == 0) {
                digitalNomadRepository.save(new DigitalNomad(11L, "1vandidroid1@gmail.com", "Andrea", "Vincze", "Andi", Gender.FEMALE, locationRepository.findById(247L).get()));
                digitalNomadRepository.save(new DigitalNomad(52L, "orbanszlrd@yahoo.com", "Szilard", "Orban", "CikiCaka", Gender.MALE, locationRepository.findById(5L).get()));
            } else {
                System.out.println(diNoCount + " digital nomads are already in database");
            }
        };
    }

    @Bean
    public CommandLineRunner insertUsers() {
        return args -> {
            System.out.println("Insert user");
            long userCount = userRepository.count();

            if (userCount == 0) {
                Location location = locationRepository.findById(1L).get();
                userRepository.save(new User(11L, "vandidroid", passwordEncoder.encode("babacilu"), true, true, true, true, "1vandidroid1@gmail.com", "Andrea", "Vincze", "Andi", Gender.FEMALE, location));
            } else {
                System.out.println(userCount + " users are already in database");
            }
        };
    }
}
