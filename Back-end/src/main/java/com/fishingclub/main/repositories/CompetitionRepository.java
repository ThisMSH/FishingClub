package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompetitionRepository extends JpaRepository<Competition, String>, JpaSpecificationExecutor<Competition> {
}