package com.fishingclub.main.dto.noRelations;

import com.fishingclub.main.dto.HuntingDTO;
import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.validations.LocalDateValidator;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CompetitionNoRelDTO {
    @NotBlank(message = "Code is required")
    @Pattern(regexp = "^[a-z]{3}-([0-2]\\d|3[0-1])-(0\\d|1[1-2])-\\d{2}$", message = "The code is invalid. Please make sure to fill the location field.")
    private String code;

    @NotNull(message = "Date is required.")
    @LocalDateValidator(message = "The date is invalid.")
    @Future(message = "You cannot create a competition in the past.")
    private LocalDate date;

    @NotNull(message = "Starting time is required.")
    private LocalDateTime startTime;

    @NotNull(message = "Ending time is required.")
    private LocalDateTime endTime;

    private int numberOfParticipants = 0;

    @NotBlank(message = "Location is required.")
    @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters.")
    private String location;

    @NotNull(message = "Amount is required.")
    @DecimalMin(value = "0", inclusive = false, message = "Amount must be greater than 0.")
    private double amount;

    private List<RankingKey> rankingIds;

    private List<Integer> huntingIds;
}
