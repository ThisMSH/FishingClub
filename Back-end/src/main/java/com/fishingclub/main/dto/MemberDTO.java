package com.fishingclub.main.dto;

import com.fishingclub.main.enums.IdentityDocumentType;
import com.fishingclub.main.validations.IdentityTypeValidator;
import com.fishingclub.main.validations.LocalDateValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDTO {
    private int number;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Family name is required.")
    private String familyName;

    @NotBlank(message = "Accession date is required.")
    @LocalDateValidator(message = "Accession date is invalid.")
    private LocalDate accessionDate;

    @NotBlank(message = "Nationality is required.")
    private String nationality;

    @NotNull(message = "Identity Document is required.")
    @IdentityTypeValidator(message = "Identity Document is invalid.")
    private IdentityDocumentType identityDocument;

    @NotBlank(message = "Identity number is required.")
    private String identityNumber;

    private List<RankingDTO> rankings;

    private List<HuntingDTO> huntings;
}
