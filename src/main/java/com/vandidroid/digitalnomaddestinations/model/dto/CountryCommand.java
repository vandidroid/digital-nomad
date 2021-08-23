package com.vandidroid.digitalnomaddestinations.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryCommand {
    @NotBlank
    @Size(min = 2, max = 100)
    @Schema(description = "The name of the country", example = "country", required = true)
    private String name;
}
