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
    private int rank;

    @Column(nullable = false)
    private int score;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
