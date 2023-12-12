package com.fishingclub.main.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RankingKey implements Serializable {
    @Column(name = "member_id")
    @Positive(message = "Member is required.")
    private int memberNumber;

    @Column(name = "competition_id")
    @NotBlank(message = "Code is required")
    @Pattern(regexp = "^[a-z]{3}-([0-2]\\d|3[0-1])-(0\\d|1[0-2])-\\d{2}$", message = "The code is invalid. Please make sure to fill the location field.")
    private String competitionCode;
}
