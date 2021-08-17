package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.dto.LocationCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.repository.CountryRepository;
import com.vandidroid.digitalnomaddestinations.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final CountryRepository countryRepository;
    private final LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location add(LocationCommand locationCommand) {
        Location location = new Location();
        location.setName(locationCommand.getName());
        Country country = countryRepository.findById(locationCommand.getCountryId()).orElseThrow(RuntimeException::new);
        location.setCountry(country);
        return locationRepository.save(location);
    }

    public Location findById(Long id) {
        return locationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }

    public Location update(Long id, LocationCommand locationCommand) {
        Location location = new Location();
        location.setId(id);
        location.setName(locationCommand.getName());
        Country country = countryRepository.findById(locationCommand.getCountryId()).orElseThrow(RuntimeException::new);
        location.setCountry(country);
        return locationRepository.save(location);
    }
}