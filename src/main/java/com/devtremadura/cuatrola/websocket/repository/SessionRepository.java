package com.devtremadura.cuatrola.websocket.repository;

import com.devtremadura.cuatrola.websocket.handlers.Session;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findById(String roomCode);

    List<Session> findAll();
}
