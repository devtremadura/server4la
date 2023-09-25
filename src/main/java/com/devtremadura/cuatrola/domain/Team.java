package com.devtremadura.cuatrola.domain;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(generator = "teams_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "teams_sequence", sequenceName = "teams_sequence", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private Integer totalScore = 0;

    @Column()
    private Integer points = 0;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
