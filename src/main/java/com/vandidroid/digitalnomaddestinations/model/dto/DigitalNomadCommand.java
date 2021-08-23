package com.vandidroid.digitalnomaddestinations.model.dto;

import com.vandidroid.digitalnomaddestinations.model.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "The email of the nomad", example = "sample@email.com", required = true)
    private String email;

    @Size(min = 2, max = 50)
    @Schema(description = "The first name of the nomad", example = "John")
    private String firstName;

    @Size(min = 2, max = 50)
    @Schema(description = "The last name of the nomad", example = "Doe")
    private String lastName;

    @NotNull
    @Size(min = 2, max = 20)
    @Schema(description = "The nickname of the nomad", example = "Joe", required = true)
    private String nickname;

    @Schema(description = "The gender of the nomad", example = "MALE")
    private Gender gender;

    @NotNull
    @Min(1)
    @Schema(description = "The id of the location", example = "1", required = true)
    private Long locationId;
}
