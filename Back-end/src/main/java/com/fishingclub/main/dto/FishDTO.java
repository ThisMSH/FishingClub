package com.fishingclub.main.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FishDTO {
    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Average weight is required.")
    private double averageWeight;

    private LevelDTO level;

    private List<HuntingDTO> huntings;
}
