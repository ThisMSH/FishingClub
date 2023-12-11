package com.fishingclub.main.dto.noRelations;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FishNoRelDTO {
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    private String name;

    @NotNull(message = "Average weight is required.")
    @DecimalMin(value = "0", inclusive = false, message = "Average weight must be greater than 0.")
    private double averageWeight;

    @Positive(message = "Level is required.")
    private int levelCode;

    private List<Integer> huntingIds;
}
