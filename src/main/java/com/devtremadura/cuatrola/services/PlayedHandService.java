package com.devtremadura.cuatrola.services;

import com.devtremadura.cuatrola.domain.redis.PlayedHand;

public interface PlayedHandService {

    PlayedHand savePlayedHand(PlayedHand playedHand);
}
