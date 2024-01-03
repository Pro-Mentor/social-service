package com.github.promentor.utils;

import io.vertx.core.json.JsonObject;

public class IncomingMessageConverter {

    public static JsonObject getMessageFromByteStream(byte[] payloadBytes) {
        return new JsonObject(new String(payloadBytes));
    }

}
