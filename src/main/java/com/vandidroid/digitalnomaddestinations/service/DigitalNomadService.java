package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.dto.DigitalNomadCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.repository.DigitalNomadRepository;
import com.vandidroid.digitalnomaddestinations.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DigitalNomadService {
    private final DigitalNomadRepository digitalNomadRepository;
    private final LocationRepository locationRepository;

    public List<DigitalNomad> findAll() {
        return digitalNomadRepository.findAll();
    }

    public DigitalNomad findById(Long id) {
        return digitalNomadRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id) {
        digitalNomadRepository.deleteById(id);
    }

    public DigitalNomad add(DigitalNomadCommand digitalNomadCommand) {
        DigitalNomad digitalNomad = new DigitalNomad();
        digitalNomad.setFirstName(digitalNomadCommand.getFirstName());
        digitalNomad.setLastName(digitalNomadCommand.getLastName());
        digitalNomad.setNickname(digitalNomadCommand.getNickname());
        digitalNomad.setEmail(digitalNomadCommand.getEmail());
        digitalNomad.setGender(digitalNomadCommand.getGender());
        Location location = locationRepository.findById(digitalNomadCommand.getLocationId()).orElseThrow(RuntimeException::new);
        digitalNomad.setLocation(location);

        return digitalNomadRepository.save(digitalNomad);
    }

    public DigitalNomad update(Long id, DigitalNomadCommand digitalNomadCommand) {
        DigitalNomad digitalNomad = digitalNomadRepository.findById(id).orElseThrow(RuntimeException::new);
        digitalNomad.setId(id);
        digitalNomad.setFirstName(digitalNomadCommand.getFirstName());
        digitalNomad.setLastName(digitalNomadCommand.getLastName());
        digitalNomad.setNickname(digitalNomadCommand.getNickname());
        digitalNomad.setEmail(digitalNomadCommand.getEmail());
        digitalNomad.setGender(digitalNomadCommand.getGender());
        Location location = locationRepository.findById(digitalNomadCommand.getLocationId()).orElseThrow(RuntimeException::new);
        digitalNomad.setLocation(location);

        return digitalNomadRepository.save(digitalNomad);
    }

    public DigitalNomad relocate(Long id, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(RuntimeException::new);
        DigitalNomad digitalNomad = findById(id);
        digitalNomad.setLocation(location);

        return digitalNomadRepository.save(digitalNomad);
    }
}