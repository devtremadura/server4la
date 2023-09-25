package com.devtremadura.cuatrola.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playercards")
public class PlayerCard {

    @Id
    @Column
    private Long id;

    @OneToOne()
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToOne()
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne()
    @JoinColumn(name = "game_id")
    private Game game;

}
