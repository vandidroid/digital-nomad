package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.ContinentCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Continent;
import com.vandidroid.digitalnomaddestinations.service.ContinentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/continents")
@Tag(name = "The Continent API", description = "The Continent API")
public class ContinentController {
    private final ContinentService continentService;

    @GetMapping(value = "", produces = {"application/json"})
    public List<Continent> findAll() {
        return continentService.findAll();
    }

    @PostMapping(value = "", produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public Continent add(@Valid @RequestBody ContinentCommand continentCommand) {
        return continentService.add(continentCommand);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public Continent findById(@PathVariable Long id) {
        return continentService.findById(id);
    }

    @PutMapping(value = "/{id}", produces = {"application/json"})
    public Continent update(@Parameter(description = "The id of the continent", required = true, example = "1") @Min(1) @PathVariable Long id, @Valid @RequestBody ContinentCommand continentCommand) {
        return continentService.update(id, continentCommand);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "The id of the continent", required = true, example = "1") @Min(1) @PathVariable Long id) {
        continentService.deleteById(id);
    }
}
