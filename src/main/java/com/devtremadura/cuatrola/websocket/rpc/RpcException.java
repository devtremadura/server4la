package com.devtremadura.cuatrola.websocket.rpc;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class RpcException extends Exception{

    private final RpcErrorCode errorCode;
    private final String errorDescription;
    private final JsonNode errorDetail;

    public RpcException(RpcErrorCode errorCode, String errorDescription, JsonNode errorDetail) {
        super("[" + errorCode + "]: " + errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorDetail = errorDetail;
    }

    @Override
    public String toString() {
        return RpcException.class.getSimpleName() + getMessage() +
                (errorDetail != null ? "; detail:\n" + errorDetail : "");

    }

}
