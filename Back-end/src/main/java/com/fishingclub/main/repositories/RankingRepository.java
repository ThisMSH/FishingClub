package com.fishingclub.main.repositories;

import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingKey> {
    List<Ranking> findAllByCompetitionCodeOrderByScoreDesc(String competitionCode);
    @Query("SELECT COUNT(r) FROM Ranking r WHERE r.id.competitionCode = :code")
    Integer getMembersCount(@Param("code") String code);
}
