package com.devtremadura.cuatrola.websocket.rpc;

import com.devtremadura.cuatrola.websocket.handlers.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class RpcSessionFactoryImpl implements RpcSessionFactory {
    @Override
    public RpcSession createRpcSession(WebSocketSession webSocketSession) {
        return new Session(webSocketSession);
    }
}
