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
    private int code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int points;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "level", fetch = FetchType.EAGER)
    private List<Fish> fishes;
}
