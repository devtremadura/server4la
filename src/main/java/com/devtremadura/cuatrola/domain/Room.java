package com.devtremadura.cuatrola.domain;

import java.util.List;

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
@Table(name = "rooms")
public class Room implements Score{

    @Id
    @GeneratedValue(generator = "rooms_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rooms_sequence", sequenceName = "rooms_sequence", allocationSize = 1)
    @Column
    private Long id;

    @OneToMany(mappedBy = "room")
    private List<Team> teams;

    @Column
    private String code;


    @OneToMany(mappedBy = "room")
    private List<Game> game;

}
