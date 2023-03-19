package com.vandidroid.digitalnomaddestinations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DigitalNomadDestinationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DigitalNomadDestinationsApplication.class, args);
    }
}
