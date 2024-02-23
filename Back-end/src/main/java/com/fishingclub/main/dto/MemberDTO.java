package com.fishingclub.main.dto;

import com.fishingclub.main.dto.noRelations.HuntingNoRelDTO;
import com.fishingclub.main.dto.noRelations.RankingNoRelDTO;
import com.fishingclub.main.enums.IdentityDocumentType;
import com.fishingclub.main.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDTO {
    private int number;
    private String name;
    private String familyName;
    private String username;
    private String password;
    private UserRole userRole;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    private List<RankingNoRelDTO> rankings;
    private List<HuntingDTO> huntings;
}
