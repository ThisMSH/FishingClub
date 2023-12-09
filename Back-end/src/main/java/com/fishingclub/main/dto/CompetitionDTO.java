package com.fishingclub.main.dto;

import com.fishingclub.main.validations.LocalDateValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CompetitionDTO {
    @NotBlank(message = "Code is required")
    @Pattern(regexp = "^[a-z]{3}-([0-2]\\d|3[0-1])-(0\\d|1[1-2])-\\d{2}$", message = "The code is invalid. Please make sure to fill the location field.")
    private String code;

    @NotBlank(message = "Date is required.")
    @LocalDateValidator(message = "The date is invalid.")
    private LocalDate date;

    @NotBlank(message = "Starting time is required.")
    private LocalDateTime startTime;

    @NotBlank(message = "Ending time is required.")
    private LocalDateTime endTime;

    private int numberOfParticipants = 0;

    @NotBlank(message = "Location is required.")
    private String location;

    @NotBlank(message = "Amount is required.")
    private double amount;

    private List<RankingDTO> rankings;

    private List<HuntingDTO> huntings;
}
