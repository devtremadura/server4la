package com.devtremadura.cuatrola.domain;

import java.util.List;


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
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(generator = "players_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "players_sequence", sequenceName = "players_sequence", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private Boolean alone;

    @Column
    private Boolean cuatrola;

    @Column
    private Integer shoutedTwenty;

    @Column
    private Boolean shoutedForty;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;


}
