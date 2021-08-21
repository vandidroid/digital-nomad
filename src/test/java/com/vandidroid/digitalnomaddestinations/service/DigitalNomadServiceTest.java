package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.Gender;
import com.vandidroid.digitalnomaddestinations.model.dto.DigitalNomadCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.repository.DigitalNomadRepository;
import com.vandidroid.digitalnomaddestinations.repository.LocationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DigitalNomadServiceTest {
    private static final DigitalNomadRepository digitalNomadRepository = Mockito.mock(DigitalNomadRepository.class);
    private static final LocationRepository locationRepository = Mockito.mock(LocationRepository.class);

    private static DigitalNomadService digitalNomadService;

    private static List<DigitalNomad> digitalNomads = List.of(new DigitalNomad(1L, "john.doe@gmail.com", "John", "Doe", "Joe", Gender.MALE, new Location(1L, "Chicago", new Country(1L, "USA"))));

    @BeforeAll
    static void beforeAll() {
        digitalNomadService = new DigitalNomadService(digitalNomadRepository, locationRepository);
    }

    @Test
    void findAll() {
        when(digitalNomadRepository.findAll()).thenReturn(digitalNomads);
        List<DigitalNomad> list = digitalNomadService.findAll();
        assertThat(list.size()).isEqualTo(digitalNomads.size());
        assertThat(list.contains(digitalNomads.get(0))).isTrue();
    }

    @Test
    void findById() {
        long id = 1;
        when(digitalNomadRepository.findById(id)).thenReturn(Optional.of(digitalNomads.get(0)));
        DigitalNomad digitalNomad = digitalNomadService.findById(id);
        assertThat(digitalNomad.getId()).isEqualTo(id);
        assertThat(digitalNomad.getEmail()).isEqualTo(digitalNomads.get(0).getEmail());
    }

    @Test
    void deleteById() {
        Long id = 1L;
        doNothing().when(digitalNomadRepository).deleteById(id);
        digitalNomadService.deleteById(id);
        verify(digitalNomadRepository, times(1)).deleteById(id);
    }

    @Test
    void add() {
        Location location = new Location(1L, "Boston", new Country(1L, "USA"));

        DigitalNomad digitalNomad = new DigitalNomad();
        digitalNomad.setEmail("jane.doe@gmail.com");
        digitalNomad.setNickname("Jane");
        digitalNomad.setLocation(location);

        DigitalNomadCommand digitalNomadCommand = new DigitalNomadCommand();
        digitalNomadCommand.setEmail("jane.doe@gmail.com");
        digitalNomadCommand.setNickname("Jane");
        digitalNomadCommand.setLocationId(1L);

        when(locationRepository.findById(1l)).thenReturn(Optional.of(location));
        when(digitalNomadRepository.save(digitalNomad)).thenReturn(digitalNomad);

        DigitalNomad newNomad = digitalNomadService.add(digitalNomadCommand);

        assertThat(newNomad.getEmail()).isEqualTo(digitalNomadCommand.getEmail());
        assertThat(newNomad.getLocation().getId()).isEqualTo(digitalNomadCommand.getLocationId());
    }

    @Test
    void update() {
        Location location = new Location(1L, "Boston", new Country(1L, "USA"));

        DigitalNomad digitalNomad = new DigitalNomad();
        digitalNomad.setId(1L);
        digitalNomad.setEmail("jane.doe@gmail.com");
        digitalNomad.setNickname("Jane");
        digitalNomad.setLocation(location);

        DigitalNomadCommand digitalNomadCommand = new DigitalNomadCommand();
        digitalNomadCommand.setEmail("jane.doe@gmail.com");
        digitalNomadCommand.setNickname("Jane");
        digitalNomadCommand.setLocationId(1L);

        when(locationRepository.findById(1l)).thenReturn(Optional.of(location));
        when(digitalNomadRepository.findById(1L)).thenReturn(Optional.of(digitalNomad));
        when(digitalNomadRepository.save(digitalNomad)).thenReturn(digitalNomad);

        DigitalNomad newNomad = digitalNomadService.update(1L, digitalNomadCommand);

        assertThat(newNomad.getEmail()).isEqualTo(digitalNomadCommand.getEmail());
        assertThat(newNomad.getLocation().getId()).isEqualTo(digitalNomadCommand.getLocationId());    }

    @Test
    void relocate() {
        Location budapest = new Location(1L, "Budapest", new Country(1L, "Hungary"));
        when(locationRepository.findById(1l)).thenReturn(Optional.of(budapest));

        Location wien = new Location(2L, "Wien", new Country(2L, "Austria"));
        when(locationRepository.findById(2l)).thenReturn(Optional.of(wien));

        DigitalNomad digitalNomad = new DigitalNomad();
        digitalNomad.setId(1L);
        digitalNomad.setLocation(wien);

        when(digitalNomadRepository.findById(1L)).thenReturn(Optional.of(digitalNomad));
        when(digitalNomadRepository.save(digitalNomad)).thenReturn(digitalNomad);

        DigitalNomad nomad = digitalNomadService.relocate(1L, 2L);

        assertThat(nomad.getLocation().getName()).isEqualTo("Wien");
    }
}