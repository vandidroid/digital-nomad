package com.vandidroid.digitalnomaddestinations.repository;

import com.vandidroid.digitalnomaddestinations.model.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {
}
