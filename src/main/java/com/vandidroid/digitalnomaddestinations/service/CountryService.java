package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.dto.CountryCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country add(CountryCommand countryCommand) {
        Country country = new Country();
        country.setName(countryCommand.getName());

        return countryRepository.save(country);
    }

    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    public Country update(Long id, CountryCommand countryCommand) {

        Country country = new Country();
        country.setId(id);
        country.setName(countryCommand.getName());

        return countryRepository.save(country);
    }
}
