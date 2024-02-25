package com.fishingclub.main.dto.noRelations;

import com.fishingclub.main.dto.HuntingDTO;
import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.enums.IdentityDocumentType;
import com.fishingclub.main.enums.UserRole;
import com.fishingclub.main.validations.IdentityTypeValidator;
import com.fishingclub.main.validations.LocalDateValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberNoRelDTO {
    private int number;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 36, message = "First name must be between 3 and 36 characters.")
    private String name;

    @NotBlank(message = "Family name is required.")
    @Size(min = 3, max = 36, message = "Family name must be between 3 and 36 characters.")
    private String familyName;

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotNull(message = "User role is required.")
    private UserRole userRole;

    @NotNull(message = "Accession date is required.")
    @LocalDateValidator(message = "Accession date is invalid.")
    private LocalDate accessionDate;

    @NotBlank(message = "Nationality is required.")
    @Size(min = 3, max = 56, message = "Nationality must be between 3 and 56 characters.")
    private String nationality;

    @NotNull(message = "Identity Document is required.")
    @IdentityTypeValidator(message = "Identity Document is invalid.")
    private IdentityDocumentType identityDocument;

    @NotBlank(message = "Identity number is required.")
    private String identityNumber;

    @NotNull(message = "Status of the account is required.")
    private Boolean isActive;

    private List<RankingKey> rankingKeys;

    private List<Integer> huntingIds;
}
