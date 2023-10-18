package com.devtremadura.cuatrola.websocket.handlers;

import com.devtremadura.cuatrola.websocket.repository.SessionRepository;
import com.devtremadura.cuatrola.websocket.rpc.AbstractRpcHandler;
import com.devtremadura.cuatrola.websocket.rpc.RpcException;
import com.devtremadura.cuatrola.websocket.rpc.RpcSession;
import com.devtremadura.cuatrola.websocket.rpc.RpcSessionFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CuatrolaWSHandler extends AbstractRpcHandler implements SessionRepository {

    private static final Logger logger = LoggerFactory.getLogger(CuatrolaWSHandler.class);
    private final Map<String, Session> sessions = new HashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public CuatrolaWSHandler(RpcSessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected void afterConnectionEstablished(RpcSession session) throws IOException {

    }

    @Override
    protected JsonNode handleAction(RpcSession session, String action, JsonNode payload) throws RpcException, ClassNotFoundException, IOException {

        logger.info("HOLA QUE TAL");

        return null;
    }

    @Override
    protected void afterConnectionClosed(RpcSession session) {

    }

    @Override
    public Optional<Session> findById(String roomCode) {
        return Optional.empty();
    }

    @Override
    public List<Session> findAll() {
        return null;
    }
}
