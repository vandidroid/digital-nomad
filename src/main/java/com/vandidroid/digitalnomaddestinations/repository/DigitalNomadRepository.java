package com.vandidroid.digitalnomaddestinations.repository;

import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DigitalNomadRepository extends JpaRepository<DigitalNomad, Long> {
    @Query("select n from DigitalNomad n join Location l on l=n.location where l.country.id = ?1")
    Set<DigitalNomad> findByCountryId(Long countryId);
}
