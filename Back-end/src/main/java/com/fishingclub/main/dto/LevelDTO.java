package com.fishingclub.main.dto;

import com.fishingclub.main.dto.noRelations.FishNoRelDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LevelDTO {
    private int code;
    private String description;
    private int points;
    private List<FishNoRelDTO> fishes;
}
