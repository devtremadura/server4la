package com.devtremadura.cuatrola.services.impl;

import com.devtremadura.cuatrola.domain.Player;
import com.devtremadura.cuatrola.repository.PlayerRepository;
import com.devtremadura.cuatrola.services.PlayerService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void savePlayer(Player p) {
        playerRepository.save(p);
    }
}
