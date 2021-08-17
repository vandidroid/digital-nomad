package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.service.DigitalNomadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public DigitalNomad add(@RequestBody DigitalNomad digitalNomad) {
        return digitalNomadService.add(digitalNomad);
    }

    @PutMapping("/{id}")
    public DigitalNomad update(@PathVariable Long id, @RequestBody DigitalNomad digitalNomad) {
        return digitalNomadService.update(id, digitalNomad);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        digitalNomadService.deleteById(id);
    }
}
