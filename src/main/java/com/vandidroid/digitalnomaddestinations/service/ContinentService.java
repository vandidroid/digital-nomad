package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.dto.ContinentCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Continent;
import com.vandidroid.digitalnomaddestinations.repository.ContinentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContinentService {
    private final ContinentRepository continentRepository;

    public List<Continent> findAll() {
        return continentRepository.findAll();
    }

    public Continent add(ContinentCommand continentCommand) {
        Continent continent = new Continent(continentCommand.getName(), continentCommand.getArea());
        return continentRepository.save(continent);
    }

    public Continent findById(Long id) {
        return continentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public long count() {
        return continentRepository.count();
    }

    public Continent update(Long id, ContinentCommand continentCommand) {
        Continent continent = continentRepository.findById(id).orElseThrow(RuntimeException::new);
        continent.setName(continentCommand.getName());
        continent.setArea(continentCommand.getArea());

        return continentRepository.save(continent);
    }

    public void deleteById(Long id) {
        continentRepository.deleteById(id);
    }
}
