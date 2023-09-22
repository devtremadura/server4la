package com.devtremadura.cuatrola.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "rooms")
public class Room {

    @Id
    @Column
    private Long id;

    @OneToMany(mappedBy = "room")
    private List<Team> teams;

    @Column
    private String code;

    @Column
    private Integer scoreGames; // deberia ser un tipo Score { a: X, b: Y }
}
