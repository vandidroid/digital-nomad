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
public class ContinentCommand {
    @NotBlank
    @Size(min = 4, max = 50)
    @Schema(description = "The name of the continent", example = "Europe", required = true)
    private String name;
    @Schema(description = "The area of the continent", example = "10530000")
    private Double area;
}
