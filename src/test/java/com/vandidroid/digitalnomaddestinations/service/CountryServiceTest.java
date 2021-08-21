package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.dto.CountryCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.repository.CountryRepository;
import com.vandidroid.digitalnomaddestinations.repository.DigitalNomadRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {
    private final static CountryRepository countryRepository = Mockito.mock(CountryRepository.class);
    private final static DigitalNomadRepository digitalNomadRepository = Mockito.mock(DigitalNomadRepository.class);

    private static CountryService countryService;

    private static List<Country> countries = List.of(new Country(1L,"Hungary"), new Country(2L,"Japan"));

    @BeforeAll
    static void beforeAll() {
        countryService = new CountryService(countryRepository, digitalNomadRepository);
    }

    @Test
    void findAll() {
        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> list = countryService.findAll();
        assertThat(list.size()).isEqualTo(countries.size());
    }

    @Test
    void add() {
        Country country = new Country("Spain");
        CountryCommand countryCommand = new CountryCommand("Spain");

        when(countryRepository.save(country)).thenReturn(country);

        Country newCountry = countryService.add(countryCommand);
        assertThat(newCountry.getName()).isEqualTo(countryCommand.getName());
    }

    @Test
    void findById() {
        long id =1;

        when(countryRepository.findById(id)).thenReturn(Optional.of(countries.get(0)));

        Country country = countryService.findById(id);
        assertThat(country.getId()).isEqualTo(id);
        assertThat(country.getName()).isEqualTo(countries.get(0).getName());
    }

    @Test
    void deleteById() {
        Long id = 1L;

        doNothing().when(countryRepository).deleteById(id);

        countryService.deleteById(id);
        verify(countryRepository, times(1)).deleteById(id);
    }

    @Test
    void update() {
        CountryCommand countryCommand = new CountryCommand("Italy");

        Country country = new Country(3L, "Italy");
        when(countryRepository.save(country)).thenReturn(country);

        Country updatedCountry = countryService.update(3L, countryCommand);
        assertThat(updatedCountry.getName()).isEqualTo(countryCommand.getName());
    }

    @Test
    void findNomadsByCountryId() {
        Long id = 1L;

        DigitalNomad digitalNomad = new DigitalNomad();
        digitalNomad.setEmail("john.doe@gmail.com");
        digitalNomad.setNickname("Joe");

        when(digitalNomadRepository.findByCountryId(id)).thenReturn(Set.of(digitalNomad));

        Set<DigitalNomad> digitalNomads = countryService.findNomadsByCountryId(id);

        assertThat(digitalNomads.size()).isEqualTo(1);
        assertThat(digitalNomads.contains(digitalNomad));
    }
}