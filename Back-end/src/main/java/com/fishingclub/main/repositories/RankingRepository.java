package com.fishingclub.main.repositories;

import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RankingRepository extends JpaRepository<Ranking, RankingKey>, JpaSpecificationExecutor<Ranking> {
}