package com.devtremadura.cuatrola.domain;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(generator = "games_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "games_sequence", sequenceName = "games_sequence", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;

}
