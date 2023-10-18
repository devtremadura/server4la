package com.devtremadura.cuatrola.websocket.rpc;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.StringJoiner;

public class CallResultMessage implements RpcMessage {


    private final String messageId;
    private final String action;
    @Getter
    private final JsonNode payload;

    public CallResultMessage(String messageId,String action, JsonNode payload) {
        this.messageId = messageId;
        this.action = action;
        this.payload = payload;
    }

    @Override
    public MessageType getType() {
        return MessageType.CALLRESULT;
    }

    @Override
    public String getMessageId() {
        return messageId;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{MessageType.CALLRESULT.getNumber(), messageId, action, payload};
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CallResultMessage.class.getSimpleName() + "[", "]")
                .add("messageId='" + messageId + "'")
                .add("payload=" + payload)
                .toString();
    }
}
