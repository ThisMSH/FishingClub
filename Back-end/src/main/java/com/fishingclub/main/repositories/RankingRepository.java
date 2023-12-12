package com.fishingclub.main.repositories;

import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingKey> {
    List<Ranking> findAllByCompetitionCodeOrderByScoreDesc(String competitionCode);
}
