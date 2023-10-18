package com.devtremadura.cuatrola.websocket.rpc;


import org.springframework.web.socket.WebSocketSession;

public interface RpcSessionFactory {
    RpcSession createRpcSession(WebSocketSession webSocketSession);

}
