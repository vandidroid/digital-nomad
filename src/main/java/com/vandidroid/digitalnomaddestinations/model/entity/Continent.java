package com.vandidroid.digitalnomaddestinations.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String name;
    private Double area;

    @JsonIgnore
    @ManyToMany
    private Set<Country> countries;

    public Continent(String name) {
        this.name = name;
    }

    public Continent(String name, Double area) {
        this.name = name;
        this.area = area;
    }

    public Continent(Long id, String name, Double area) {
        this.id = id;
        this.name = name;
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return name.equals(continent.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
