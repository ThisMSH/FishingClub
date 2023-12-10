package com.fishingclub.main.dto;

import com.fishingclub.main.dto.noRelations.HuntingNoRelDTO;
import com.fishingclub.main.dto.noRelations.LevelNoRelDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FishDTO {
    private String name;
    private double averageWeight;
    private LevelNoRelDTO level;
    private List<HuntingNoRelDTO> huntings;
}
