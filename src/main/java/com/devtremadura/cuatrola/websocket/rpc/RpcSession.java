package com.devtremadura.cuatrola.websocket.rpc;

import java.io.Closeable;
import java.io.IOException;

public interface RpcSession extends Closeable {

    void sendResponse(RpcMessage rpcMessage) throws IOException;

    void receiveResponse(RpcMessage responseMessage);

    boolean isOpen();

    @Override
    void close() throws IOException;

    void closeSessionConcurrent() throws IOException;


}
