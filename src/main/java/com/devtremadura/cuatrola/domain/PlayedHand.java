package com.devtremadura.cuatrola.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@RedisHash("playedHand")
public class PlayedHand {

    @Id
    private Long id;

    private Card pinte;
    private Player winnerPlayer;
    private List<Card> cards;

    private String handName;

}
