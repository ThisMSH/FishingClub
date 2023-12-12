package com.fishingclub.main.services.interfaces;

import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.dto.noRelations.RankingNoRelDTO;
import com.fishingclub.main.embeddables.RankingKey;

public interface IRankingService extends GenericService<RankingDTO, RankingNoRelDTO, RankingKey> {
    void update(String competitionCode);
}
