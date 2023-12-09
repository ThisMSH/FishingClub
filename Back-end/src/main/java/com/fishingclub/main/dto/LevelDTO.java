package com.fishingclub.main.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LevelDTO {
    private int code;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotBlank(message = "Points is required.")
    private int points;

    private List<FishDTO> fishes;
}
