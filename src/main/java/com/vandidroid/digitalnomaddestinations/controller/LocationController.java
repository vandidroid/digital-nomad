package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.LocationCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
@Tag(name = "The Location API", description = "The Location API")
public class LocationController {
    private final LocationService locationService;

    @GetMapping(value = "", produces = {"application/json"})
    @Operation(summary = "List all locations", description = "List all locations")
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Find a location", description = "Find a location by its id")
    public Location findById(@Parameter(description = "The id of the location", required = true, example = "1") @Min(1)  @PathVariable Long id) {
        return locationService.findById(id);
    }

    @GetMapping(value = "/{id}/nomads", produces = {"application/json"})
    @Operation(summary = "Find all nomads from a location", description = "Find all nomads from a location by the location id")
    public Set<DigitalNomad> findNomadsByLocationId(@Parameter(description = "The id of the location", required = true, example = "1") @Min(1) @PathVariable Long id) {
        return locationService.findById(id).getDigitalNomads();
    }

    @PostMapping(value = "", produces = {"application/json"})
    @Operation(summary = "Insert a new location", description = "Insert a new location")
    @ResponseStatus(HttpStatus.CREATED)
    public Location add(@Valid @RequestBody LocationCommand locationCommand) {
        return locationService.add(locationCommand);
    }

    @PutMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Update a location", description = "Update a location by its id")
    public Location update(@Parameter(description = "The id of the location", required = true, example = "1") @Min(1) @PathVariable Long id, @Valid @RequestBody LocationCommand locationCommand) {
        return locationService.update(id, locationCommand);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Delete a location", description = "Delete a location by its id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "The id of the location", required = true, example = "1") @Min(1) @PathVariable Long id) {
        locationService.deleteById(id);
    }
}
