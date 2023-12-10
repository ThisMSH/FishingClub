package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Boolean existsByDate(LocalDate date);
}
