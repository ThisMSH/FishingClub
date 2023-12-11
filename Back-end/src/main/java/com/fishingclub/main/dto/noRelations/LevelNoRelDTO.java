package com.fishingclub.main.dto.noRelations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LevelNoRelDTO {
    private int code;

    @NotBlank(message = "Description is required.")
    @Size(min = 2, max = 255, message = "Description must be between 2 and 255 characters.")
    private String description;

    @Positive(message = "Points are required and must be greater than 0.")
    private int points;

    private List<String> fishIds;
}
