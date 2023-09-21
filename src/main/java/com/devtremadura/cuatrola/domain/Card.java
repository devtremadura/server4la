package com.devtremadura.cuatrola.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column
    private String id;

    @Column
    private String value;

    @Column
    private String suit;

    @Column
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
