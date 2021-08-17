package com.vandidroid.digitalnomaddestinations.repository;

import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
