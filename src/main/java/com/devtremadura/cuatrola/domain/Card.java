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
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(generator = "card_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "card_sequence", sequenceName = "card_sequence", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private String value;

    @Column
    private String suit;

    @Column
    private Integer rating;

}
