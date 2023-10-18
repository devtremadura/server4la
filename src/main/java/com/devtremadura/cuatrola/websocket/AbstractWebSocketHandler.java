package com.devtremadura.cuatrola.websocket;

import org.springframework.web.socket.*;
import java.io.IOException;

public abstract class AbstractWebSocketHandler implements WebSocketHandler {


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
        if (message instanceof TextMessage) {
            handleTextMessage(session, (TextMessage) message);
        } else if (message instanceof BinaryMessage) {
            handleBinaryMessage(session, (BinaryMessage) message);
        } else if (message instanceof PongMessage) {
            handlePongMessage(session, (PongMessage) message);
        } else {
            throw new IllegalStateException("Unexpected WebSocket message type: " + message);
        }
    }

    private void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        handleMessage(session, message.getPayload());
    }

    protected abstract void handleMessage(WebSocketSession session, String message) throws IOException;


    private void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        //TODO handler compressed message
        try {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Binary messages not supported"));
        } catch (IOException ignore) {
        }
    }

    private void handlePongMessage(WebSocketSession session, PongMessage message) {}

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {}

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
