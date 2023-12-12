package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> findByFishNameAndCompetitionCodeAndMemberNumber(String fishName, String competitionCode, int memberNumber);
}
