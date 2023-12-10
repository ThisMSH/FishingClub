package com.fishingclub.main.dto;

import com.fishingclub.main.dto.noRelations.CompetitionNoRelDTO;
import com.fishingclub.main.dto.noRelations.FishNoRelDTO;
import com.fishingclub.main.dto.noRelations.MemberNoRelDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HuntingDTO {
    private int id;
    private int numberOfFish;
    private MemberNoRelDTO member;
    private CompetitionNoRelDTO competition;
    private FishNoRelDTO fish;
}
