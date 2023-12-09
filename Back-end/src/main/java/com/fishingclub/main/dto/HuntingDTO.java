package com.fishingclub.main.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HuntingDTO {
    private int id;

    @NotBlank(message = "Number of fishes is required.")
    private int numberOfFish;

    private MemberDTO member;

    private CompetitionDTO competition;

    private FishDTO fish;
}
