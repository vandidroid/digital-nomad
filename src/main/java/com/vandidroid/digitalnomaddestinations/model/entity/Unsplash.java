package com.vandidroid.digitalnomaddestinations.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unsplash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    @OneToOne
    private Location location;

    public Unsplash(String data, Location location) {
        this.data = data;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unsplash unsplash = (Unsplash) o;
        return id.equals(unsplash.id) && data.equals(unsplash.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }
}
