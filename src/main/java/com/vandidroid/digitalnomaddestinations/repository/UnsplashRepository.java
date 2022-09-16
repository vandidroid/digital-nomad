package com.vandidroid.digitalnomaddestinations.repository;

import com.vandidroid.digitalnomaddestinations.model.entity.Unsplash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnsplashRepository extends JpaRepository<Unsplash,Long> {
}
