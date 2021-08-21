package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.dto.LocationCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.repository.CountryRepository;
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
class LocationServiceTest {
    private static final CountryRepository countryRepository = Mockito.mock(CountryRepository.class);
    private static final LocationRepository locationRepository = Mockito.mock(LocationRepository.class);

    private static LocationService locationService;

    private final List<Location> locations = List.of(
            new Location(1L, "Budapest", new Country(1L, "Hungary")),
            new Location(2L, "Tokyo", new Country(1L, "Japan"))
    );

    @BeforeAll
    static void beforeAll() {
        locationService = new LocationService(countryRepository, locationRepository);
    }

    @Test
    void findAll() {
        when(locationRepository.findAll()).thenReturn(locations);
        List<Location> list = locationService.findAll();
        assertThat(list.size()).isEqualTo(locations.size());
        assertThat(list.get(0).getName()).isEqualTo("Budapest");
    }

    @Test
    void add() {
        Country austria = new Country(3L, "Austria");
        Location wien = new Location(3L, "Wien", austria);

        when(countryRepository.findById(austria.getId())).thenReturn(Optional.of(austria));
        when(locationRepository.save(new Location(null, "Wien", austria))).thenReturn(wien);
        Location location = locationService.add(new LocationCommand("Wien", austria.getId()));

        assertThat(location.getName()).isEqualTo(wien.getName());
        assertThat(location.getCountry().getId()).isEqualTo(wien.getCountry().getId());
        assertThat(location.getCountry().getName()).isEqualTo(wien.getCountry().getName());
    }

    @Test
    void findById() {
        long id = 1;

        when(locationRepository.findById(id)).thenReturn(Optional.of(locations.get(0)));
        Location location = locationService.findById(id);
        assertThat(location.getId()).isEqualTo(id);
        assertThat(location.getName()).isEqualTo(locations.get(0).getName());

    }

    @Test
    void deleteById() {
        Long id = 1L;
        doNothing().when(locationRepository).deleteById(id);
        locationRepository.deleteById(id);
        verify(locationRepository, times(1)).deleteById(id);
    }

    @Test
    void update() {
        Country austria = new Country(3L, "Austria");
        Location wien = new Location(3L, "Wien", austria);

        when(countryRepository.findById(austria.getId())).thenReturn(Optional.of(austria));
        when(locationRepository.save(wien)).thenReturn(wien);
        Location location = locationService.update(3L, new LocationCommand("Wien", austria.getId()));

        assertThat(location.getName()).isEqualTo(wien.getName());
        assertThat(location.getCountry().getId()).isEqualTo(wien.getCountry().getId());
        assertThat(location.getCountry().getName()).isEqualTo(wien.getCountry().getName());
    }
}