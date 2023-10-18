package com.devtremadura.cuatrola.websocket.rpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class RpcSessionImpl implements RpcSession {
    private static final Logger logger = LoggerFactory.getLogger(RpcSessionImpl.class);
    private static final long TIMEOUT_TO_RECEIVE_RESPONSE = 2 * 60 * 1000;
    private final WebSocketSession webSocketSession;

    private final ObjectMapper mapper = new ObjectMapper();
    private final Object responseLock = new Object();
    private RpcMessage response = null;

    private String pendingResponseMessageId = null;

    public RpcSessionImpl(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    @Override
    public void sendResponse(RpcMessage rpcMessage) throws IOException {
        if (!(rpcMessage instanceof CallResultMessage) && !(rpcMessage instanceof CallErrorMessage)) {
            throw new IllegalStateException(rpcMessage + " must be instance of CallResultMessage or CallErrorMessage");
        }
        sendRpcMessage(rpcMessage);
    }

    private void sendRpcMessage(RpcMessage rpcMessage) throws IOException {
        String txtMessage = mapper.writeValueAsString(rpcMessage.toArray());
        logger.trace("Sending To {} the message {}", "webSocket", txtMessage);
        TextMessage message = new TextMessage(txtMessage);
        webSocketSession.sendMessage(message);
    }

    @Override
    public void receiveResponse(RpcMessage responseMessage) {
        if (responseMessage.getType() != MessageType.CALLRESULT && responseMessage.getType() != MessageType.CALLERROR) {
            throw new IllegalArgumentException();
        }
        if (responseMessage.getMessageId().equals(pendingResponseMessageId)) {
            synchronized (responseLock) {
                pendingResponseMessageId = null;
                response = responseMessage;
                responseLock.notifyAll();
            }
        } else {
            logger.info("{} in not expected in {}", responseMessage, this);
        }
    }

    @Override
    public boolean isOpen() {
        return webSocketSession.isOpen();
    }

    @Override
    public void close() throws IOException {
        CloseStatus c = new CloseStatus(1001, "Concurrent Session, client already connected");
        webSocketSession.close(c);
    }

    @Override
    public void closeSessionConcurrent() throws IOException {

    }
}
