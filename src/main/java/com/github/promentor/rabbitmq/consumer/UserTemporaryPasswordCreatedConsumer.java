package com.github.promentor.rabbitmq.consumer;

import com.github.promentor.rabbitmq.model.User;
import com.github.promentor.utils.IncomingMessageConverter;
import io.quarkus.logging.Log;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.Arrays;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class UserTemporaryPasswordCreatedConsumer {

    @Incoming("user-temporary-password-created")
    public CompletionStage<Void> consume(Message<byte[]> message) {
        Log.info("message reserved in \"user-temporary-password-created\"");

        JsonObject jsonPayload = IncomingMessageConverter.getMessageFromByteStream(message.getPayload());
        Log.debug("Reserved message: " + jsonPayload);

        return message.ack();
    }

}
