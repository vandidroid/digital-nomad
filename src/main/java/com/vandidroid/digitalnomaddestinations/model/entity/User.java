package com.vandidroid.digitalnomaddestinations.model.entity;

import com.vandidroid.digitalnomaddestinations.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String email;
    private String firstName;
    private String lastName;
    private String nickname;
    private Gender gender;

    @ManyToOne
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id) && email.equals(that.email) && nickname.equals(that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, nickname);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", location=" + location +
                '}';
    }
}
