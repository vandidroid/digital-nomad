package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.Location;
import com.vandidroid.digitalnomaddestinations.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/{id}/nomads")
    public Set<DigitalNomad> findNomadsById(@PathVariable Long id) {
        return locationService.findById(id).getDigitalNomads();
    }

    @PostMapping("")
    public Location add(@RequestBody Location location) {
        return locationService.add(location);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        return locationService.update(id,location);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        locationService.deleteById(id);
    }
}
