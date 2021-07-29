package com.vandidroid.digitalnomaddestinations.repository;

import com.vandidroid.digitalnomaddestinations.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
