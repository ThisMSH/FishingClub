package com.fishingclub.main.entities;

import com.fishingclub.main.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private LocalDate accessionDate;

    @Column(nullable = false)
    private String nationality;

    @Enumerated
    @Column(nullable = false)
    private IdentityDocumentType identityDocument;

    @Column(nullable = false)
    private String identityNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Ranking> rankings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Hunting> huntings;
}
