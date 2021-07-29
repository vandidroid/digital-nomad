package com.vandidroid.digitalnomaddestinations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DigitalNomad {
    @Id
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String nickname;
    private Gender gender;
    @ManyToOne
    private Location location;
}
