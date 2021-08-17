package com.vandidroid.digitalnomaddestinations.model.dto;

import com.vandidroid.digitalnomaddestinations.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitalNomadCommand {
    @Email
    @NotNull
    @Size(min = 10, max = 50)
    private String email;

    @Size(min = 2, max = 50)
    private String firstName;

    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 20)
    private String nickname;

    private Gender gender;

    @NotNull
    @Min(1)
    private Long locationId;
}
