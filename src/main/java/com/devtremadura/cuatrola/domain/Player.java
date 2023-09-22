package com.devtremadura.cuatrola.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "players")
public class Player {

    @Id
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

    @OneToMany(mappedBy = "player")
    private List<Card> cards;

}
