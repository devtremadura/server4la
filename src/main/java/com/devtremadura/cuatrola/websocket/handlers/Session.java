package com.devtremadura.cuatrola.websocket.handlers;

import com.devtremadura.cuatrola.websocket.rpc.RpcSessionImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

@Getter
public class Session extends RpcSessionImpl {

    private final static ObjectMapper mapper = new ObjectMapper();

    private final String roomCode;
    //private final Long userId;
    /**
     * userId
     * roomCode
     *
     *
     * @param webSocketSession Web socket session
     */


    public Session(WebSocketSession webSocketSession) {
        super(webSocketSession);
        String path = Objects.requireNonNull(webSocketSession.getUri()).getPath();
        //this.userId = Long.parseLong(path.split("/")[1]);
        this.roomCode = path.split("/")[2];
    }

}
