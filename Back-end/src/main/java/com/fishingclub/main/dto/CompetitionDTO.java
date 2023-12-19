package com.fishingclub.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CompetitionDTO {
    private String code;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int numberOfParticipants;
    private String location;
    private double amount;
    private List<RankingDTO> rankings;
    private List<HuntingDTO> huntings;
}
