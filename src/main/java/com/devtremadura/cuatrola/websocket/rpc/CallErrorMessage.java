package com.devtremadura.cuatrola.websocket.rpc;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.StringJoiner;

public class CallErrorMessage implements RpcMessage {

    private final String messageId;
    private final String action;
    private final RpcErrorCode errorCode;
    private final String errorDescription;
    private final JsonNode errorDetail;

    public CallErrorMessage(String messageId, String action, RpcErrorCode errorCode, String errorDescription, JsonNode errorDetail) {
        this.messageId = messageId;
        this.action = action;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorDetail = errorDetail;
    }

    public CallErrorMessage(String messageId, RpcException rpcException) {
        this(messageId,"", rpcException.getErrorCode(), rpcException.getErrorDescription(), rpcException.getErrorDetail());
    }

    @Override
    public MessageType getType() {
        return MessageType.CALLERROR;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public String getMessageId() {
        return messageId;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{MessageType.CALLERROR.getNumber(), messageId, errorCode, errorDescription, errorDetail};
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CallErrorMessage.class.getSimpleName() + "[", "]")
                .add("messageId='" + messageId + "'")
                .add("errorCode='" + errorCode + "'")
                .add("errorDescription='" + errorDescription + "'")
                .add("errorDetail=" + errorDetail)
                .toString();
    }
}
