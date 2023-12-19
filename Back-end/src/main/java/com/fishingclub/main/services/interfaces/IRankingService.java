package com.fishingclub.main.services.interfaces;

import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.dto.noRelations.RankingNoRelDTO;
import com.fishingclub.main.embeddables.RankingKey;

import java.util.List;

public interface IRankingService extends GenericService<RankingDTO, RankingNoRelDTO, RankingKey> {
    RankingDTO delete(String competitionCode, Integer memberNumber);
    void update(String competitionCode);
    List<RankingDTO> getByCompetitionCode(String competitionCode);
}
