package com.fishingclub.main.entities;

import com.fishingclub.main.embeddables.RankingKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rankings")
public class Ranking {
    @EmbeddedId
    private RankingKey id;

    @Column(nullable = false)
    private int rank = 0;

    @Column(nullable = false)
    private int score = 0;

    @ManyToOne
    @MapsId("memberNumber")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @MapsId("competitionCode")
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
