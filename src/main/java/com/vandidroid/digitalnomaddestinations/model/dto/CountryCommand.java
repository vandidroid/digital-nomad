package com.vandidroid.digitalnomaddestinations.model.dto;

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
    private String name;
}
