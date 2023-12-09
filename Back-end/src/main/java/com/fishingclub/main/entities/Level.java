package com.fishingclub.main.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int points;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "level")
    private List<Fish> fishes;
}
