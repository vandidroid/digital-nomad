package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.Location;
import com.vandidroid.digitalnomaddestinations.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("")
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable Long id) {
        return locationService.findById(id);
    }

    @PostMapping("")
    public Location add(@RequestBody Location location) {
        return locationService.save(location);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        location.setId(id);
        return locationService.save(location);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        locationService.deleteById(id);
    }
}
