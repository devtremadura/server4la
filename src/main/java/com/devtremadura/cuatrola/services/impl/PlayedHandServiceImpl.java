package com.devtremadura.cuatrola.services.impl;

import com.devtremadura.cuatrola.domain.redis.PlayedHand;
import com.devtremadura.cuatrola.repository.PlayedHandRepositoryRedis;
import com.devtremadura.cuatrola.services.PlayedHandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional()
public class PlayedHandServiceImpl implements PlayedHandService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlayedHandServiceImpl.class);


    @Autowired
    PlayedHandRepositoryRedis playedHandRepositoryRedis;


    public PlayedHand savePlayedHand(PlayedHand playedHand) {
        LOGGER.info("SAVING HAND {}", playedHand.getHandName());
        return playedHandRepositoryRedis.save(playedHand);
    }

}
