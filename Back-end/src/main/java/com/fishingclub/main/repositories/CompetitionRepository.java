package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Boolean existsByDate(LocalDate date);
    Page<Competition> findAllByEndTimeBefore(LocalDateTime startTime, Pageable pageable);
    Page<Competition> findAllByStartTimeAfter(LocalDateTime endTime, Pageable pageable);
    Page<Competition> findAllByStartTimeBeforeAndEndTimeAfter(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
