package com.vandidroid.digitalnomaddestinations.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private String capital;
    private Long population;
    private Double area;
    private String flag;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private Set<Location> locations;

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, Double area, Long population) {
        this.name = name;
        this.area = area;
        this.population = population;
    }

    public Country(Long id, String name, Double area, Long population) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) && Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", area=" + area +
                '}';
    }
}
