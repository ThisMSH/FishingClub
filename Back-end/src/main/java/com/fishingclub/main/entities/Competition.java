package com.fishingclub.main.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int numberOfParticipants = 0;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private double amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competition")
    private List<Ranking> rankings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competition")
    private List<Hunting> huntings;
}
