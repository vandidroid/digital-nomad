package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.CountryCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Country add(@Valid @RequestBody CountryCommand countryCommand) {
        return countryService.add(countryCommand);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable Long id, @Valid @RequestBody CountryCommand countryCommand) {
        return countryService.update(id, countryCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}
