package com.devtremadura.cuatrola.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@RedisHash("playedHand")
public class PlayedHand {

    @Id
    private String key;

    private String handName;

}
