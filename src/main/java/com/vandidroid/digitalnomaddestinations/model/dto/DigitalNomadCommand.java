package com.vandidroid.digitalnomaddestinations.model.dto;

import com.vandidroid.digitalnomaddestinations.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitalNomadCommand {
    private String email;
    private String firstName;
    private String lastName;
    private String nickname;
    private Gender gender;
    private Long locationId;
}
