package com.vandidroid.digitalnomaddestinations.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationCommand {
    @NotNull
    @Size(min = 2, max = 50)
    @Schema(description = "The name of the location", example = "location", required = true)
    private String name;

    @NotNull
    @Min(1)
    @Schema(description = "The id of the country", example = "1", required = true)
    private Long countryId;
}
