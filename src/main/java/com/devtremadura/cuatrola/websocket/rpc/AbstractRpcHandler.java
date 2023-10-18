package com.devtremadura.cuatrola.websocket.rpc;

import com.devtremadura.cuatrola.websocket.AbstractWebSocketHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public abstract class AbstractRpcHandler extends AbstractWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRpcHandler.class);


    private static final String RPC_SESSION_KEY = "RPC Session";
    private final ObjectMapper mapper = new ObjectMapper();
    private final RpcSessionFactory sessionFactory;

    protected AbstractRpcHandler(RpcSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Invoked after WebSocket negotiation has succeeded and the RPC connection is
     * opened and ready for use.
     */
    protected abstract void afterConnectionEstablished(RpcSession session) throws IOException;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        RpcSession rpcSession = sessionFactory.createRpcSession(session);
        logger.debug("New {}", rpcSession);
        session.getAttributes().put(RPC_SESSION_KEY, rpcSession);
        afterConnectionEstablished(rpcSession);
    }


    @Override
    protected void handleMessage(WebSocketSession session, String message) throws IOException {
        RpcSession rpcSession = (RpcSession) session.getAttributes().get(RPC_SESSION_KEY);
        if (rpcSession == null) {
            session.close(CloseStatus.POLICY_VIOLATION.withReason("Missed RPC session"));
            logger.warn("There is no RCP session associated with {}", session);
            return;
        }

        String messageId = "-1";
        try {
            JsonNode rpcMessage;
            try {
                if((message.charAt(0)  == '\"' && message.charAt(message.length()-1) == '\"') ||
                        ("'".equals(String.valueOf(message.charAt(0)))  && "'".equals(String.valueOf(message.charAt(message.length()-1))))) {
                    message = message.substring(1, message.length()-1);
                }
                rpcMessage = mapper.readTree(message);
            } catch (Exception exception) {
                throw new RpcException(RpcErrorCode.RpcFrameworkError, "Invalid RPC Message", TextNode.valueOf(message));
            }

            if (!rpcMessage.isArray() || rpcMessage.size() < 3 || !rpcMessage.get(0).isInt() || !rpcMessage.get(1).isTextual()) {
                throw new RpcException(RpcErrorCode.RpcFrameworkError, "Invalid RPC Message", rpcMessage);
            }
            MessageType messageType = MessageType.valueOf(rpcMessage.get(0).asInt());
            if (messageType == null) {
                throw new RpcException(RpcErrorCode.MessageTypeNotSupported, "RPC message type [" + rpcMessage.get(0).asInt() + "] not supported", rpcMessage);
            }
            messageId = rpcMessage.get(1).asText("-1");
            switch (messageType) {
                case CALL -> {
                    if (!rpcMessage.get(2).isTextual()) {
                        throw new RpcException(RpcErrorCode.RpcFrameworkError, "Action [" + rpcMessage.get(2) + "] is not Text", rpcMessage);
                    }
                    String action = rpcMessage.get(2).asText();
                    JsonNode payload = rpcMessage.size() == 4 ? rpcMessage.get(3) : null;
                    RpcMessage result = handleCallMessage(rpcSession, new CallMessage(messageId, action, payload));
                    rpcSession.sendResponse(result);
                }
                case CALLRESULT -> rpcSession.receiveResponse(new CallResultMessage(messageId, rpcMessage.get(2).asText(), rpcMessage.get(3)));
                case CALLERROR -> {
                    RpcErrorCode rpcErrorCode;
                    try {
                        rpcErrorCode = RpcErrorCode.valueOf(rpcMessage.get(2).asText());
                    } catch (Exception exception) {
                        throw new RpcException(RpcErrorCode.RpcFrameworkError, "ErrorCode [" + rpcMessage.get(2) + "] unknown", rpcMessage);
                    }
                    String errorDescription = rpcMessage.size() > 3 && rpcMessage.get(3).isTextual() ? rpcMessage.get(3).asText() : "";
                    JsonNode errorDetail = rpcMessage.size() == 5 ? rpcMessage.get(4) : null;
                    rpcSession.receiveResponse(new CallErrorMessage(messageId, rpcMessage.get(2).asText(), rpcErrorCode, errorDescription, errorDetail));
                }
            }
        } catch (RpcException rpcException) {
            logger.warn(session + ": " + rpcException);
            CallErrorMessage callErrorMessage = new CallErrorMessage(messageId, rpcException);
            rpcSession.sendResponse(callErrorMessage);
        }
    }

    private RpcMessage handleCallMessage(RpcSession session, CallMessage callMessage) throws RpcException {
        try {
            JsonNode resultPayload = handleAction(session, callMessage.getAction(), callMessage.getPayload());
            return new CallResultMessage(callMessage.getMessageId(), callMessage.getAction(), resultPayload);
        } catch (RpcException rpcException) {
            throw rpcException;
        } catch (Exception exception) {
            throw new RpcException(RpcErrorCode.InternalError, exception.getMessage(), null);
        }
    }

    protected abstract JsonNode handleAction(RpcSession session, String action, JsonNode payload) throws RpcException, ClassNotFoundException, IOException;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        RpcSession rpcSession = (RpcSession) session.getAttributes().get(RPC_SESSION_KEY);
        if (rpcSession != null) {
            logger.debug("{} closed with {}", rpcSession, status);
            afterConnectionClosed(rpcSession);
        }
    }

    /**
     * Invoked after the RPC connection has been closed by either side, or after a
     * transport error has occurred. Although the session may technically still be open,
     * depending on the underlying implementation, sending messages at this point is
     * discouraged and most likely will not succeed.
     */
    protected abstract void afterConnectionClosed(RpcSession session);


}
