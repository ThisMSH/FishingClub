package com.fishingclub.main.dto;

import com.fishingclub.main.dto.noRelations.HuntingNoRelDTO;
import com.fishingclub.main.dto.noRelations.RankingNoRelDTO;
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
    private int numberOfParticipants = 0;
    private String location;
    private double amount;
    private List<RankingNoRelDTO> rankings;
    private List<HuntingNoRelDTO> huntings;
}
