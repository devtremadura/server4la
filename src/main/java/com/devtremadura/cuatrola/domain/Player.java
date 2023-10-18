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
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(generator = "players_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "players_sequence", sequenceName = "players_sequence", allocationSize = 1)
    @Column
    private Long id;

    @Builder.Default
    @Column
    private Boolean alone = false;

    @Builder.Default
    @Column
    private Boolean cuatrola = false;

    @Builder.Default
    @Column
    private Integer shoutedTwenty = 0;

    @Builder.Default
    @Column
    private Boolean shoutedForty = false;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
