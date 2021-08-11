package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.Country;
import com.vandidroid.digitalnomaddestinations.model.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.Location;
import com.vandidroid.digitalnomaddestinations.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("")
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable Long id) {
        return countryService.findById(id);
    }

    @GetMapping("/{id}/locations")
    public Set<Location> findLocationsByCountryId(@PathVariable Long id) {
        return countryService.findById(id).getLocations();
    }

    @GetMapping("/{id}/nomads")
    public Set<DigitalNomad> findDigitalNomadsByCountryId(@PathVariable Long id) {
        // TODO
        return new HashSet<>();
    }

    @PostMapping("")
    public Country add(@RequestBody Country country) {
        return countryService.save(country);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable Long id, @RequestBody Country country) {
        country.setId(id);
        return countryService.save(country);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}
