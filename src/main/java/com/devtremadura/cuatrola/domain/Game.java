package com.devtremadura.cuatrola.domain;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    private Long id;

    private Team winnerTeam;
}
