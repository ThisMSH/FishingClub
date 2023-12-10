package com.fishingclub.main.dto;

import com.fishingclub.main.dto.noRelations.CompetitionNoRelDTO;
import com.fishingclub.main.dto.noRelations.MemberNoRelDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankingDTO {
    private int rank;
    private int score;
    private MemberNoRelDTO member;
    private CompetitionNoRelDTO competition;
}
