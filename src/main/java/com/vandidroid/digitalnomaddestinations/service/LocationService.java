package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.Location;
import com.vandidroid.digitalnomaddestinations.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location add(Location location) {
        return locationRepository.save(location);
    }

    public Location findById(Long id) {
        return locationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }

    public Location update(Long id, Location location) {
        location.setId(id);
        return locationRepository.save(location);
    }
}