package com.fishingclub.main.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fishes")
public class Fish {
    @Id
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double averageWeight;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fish")
    private List<Hunting> huntings;
}
