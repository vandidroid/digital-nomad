package com.vandidroid.digitalnomaddestinations.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationCommand {
    private String name;
    private Long countryId;
}
