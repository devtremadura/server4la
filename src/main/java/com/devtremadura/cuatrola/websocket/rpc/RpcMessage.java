package com.devtremadura.cuatrola.websocket.rpc;

public interface RpcMessage {

    MessageType getType();

    String getAction();

    String getMessageId();

    Object[] toArray();


}
