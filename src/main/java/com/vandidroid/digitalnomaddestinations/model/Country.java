package com.vandidroid.digitalnomaddestinations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {
    @Id
    private Long id;
    private String name;
    @OneToMany
    private Set<Location> locations;
}
