package com.devtremadura.cuatrola.websocket.rpc;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.StringJoiner;

@Getter
public class CallMessage implements RpcMessage{

    private final String messageId;
    private final String action;
    private final JsonNode payload;

    public CallMessage(String messageId, String action, JsonNode payload) {
        this.messageId = messageId;
        this.action = action;
        this.payload = payload;
    }

    @Override
    public MessageType getType() {
        return MessageType.CALL;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{MessageType.CALL.getNumber(), messageId, action, payload};
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CallMessage.class.getSimpleName() + "[", "]")
                .add("messageId='" + messageId + "'")
                .add("action='" + action + "'")
                .add("payload=" + payload)
                .toString();
    }
}
