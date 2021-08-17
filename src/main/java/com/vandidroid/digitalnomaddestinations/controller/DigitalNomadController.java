package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.DigitalNomadCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.service.DigitalNomadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nomads")
public class DigitalNomadController {
    private final DigitalNomadService digitalNomadService;

    @GetMapping("")
    public List<DigitalNomad> findAll() {
        return digitalNomadService.findAll();
    }

    @GetMapping("/{id}")
    public DigitalNomad findById(@PathVariable Long id) {
        return digitalNomadService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public DigitalNomad add(@Valid @RequestBody DigitalNomadCommand digitalNomadCommand) {
        return digitalNomadService.add(digitalNomadCommand);
    }

    @PutMapping("/{id}")
    public DigitalNomad update(@PathVariable Long id, @Valid @RequestBody DigitalNomadCommand digitalNomadCommand) {
        return digitalNomadService.update(id, digitalNomadCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        digitalNomadService.deleteById(id);
    }
}
