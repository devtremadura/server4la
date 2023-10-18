package com.devtremadura.cuatrola.websocket.rpc;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toMap;

@Getter
public enum MessageType {

    CALL(2), CALLRESULT(3), CALLERROR(4);
    public static final Map<Integer, MessageType> lookup = unmodifiableMap(Arrays.stream(values()).collect(toMap(messageType -> messageType.number, messageType -> messageType)));


    private final int number;

    public static MessageType valueOf(int num) {return lookup.get(num);}

    MessageType(int number) {
        this.number = number;
    }

}
