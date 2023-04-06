package com.vandidroid.digitalnomaddestinations;

import com.vandidroid.digitalnomaddestinations.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class DigitalNomadDestinationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DigitalNomadDestinationsApplication.class, args);
    }
}
