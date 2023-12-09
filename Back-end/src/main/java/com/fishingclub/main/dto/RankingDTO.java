package com.fishingclub.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankingDTO {
    private int rank;

    private int score;

    private MemberDTO member;

    private CompetitionDTO competition;
}
