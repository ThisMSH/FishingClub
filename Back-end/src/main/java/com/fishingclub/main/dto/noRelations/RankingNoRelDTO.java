package com.fishingclub.main.dto.noRelations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankingNoRelDTO {
    private int rank;

    private int score;

    @Positive(message = "Member is required.")
    private int member;

    @NotBlank(message = "Competition is required.")
    @Pattern(regexp = "^[a-z]{3}-([0-2]\\d|3[0-1])-(0\\d|1[1-2])-\\d{2}$", message = "Competition code is invalid.")
    private String competition;
}
