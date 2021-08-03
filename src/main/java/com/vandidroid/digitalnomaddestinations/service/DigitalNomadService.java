package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.repository.DigitalNomadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DigitalNomadService {
    private final DigitalNomadRepository digitalNomadRepository;

    public List<DigitalNomad> findAll() {
        return digitalNomadRepository.findAll();
    }

    public DigitalNomad save(DigitalNomad digitalNomad) {
        return digitalNomadRepository.save(digitalNomad);
    }

    public DigitalNomad findById(Long id) {
        return digitalNomadRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id) {
        digitalNomadRepository.deleteById(id);
    }
}