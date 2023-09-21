package com.devtremadura.cuatrola.domain;

import java.util.List;

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
@Table(name = "teams")
public class Team {

    @Id
    @Column
    private String id;

    @Column
    private String totalScore;

    @Column
    private String points;

    @Column
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
