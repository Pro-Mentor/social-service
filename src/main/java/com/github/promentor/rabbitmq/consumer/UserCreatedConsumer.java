package com.github.promentor.rabbitmq.consumer;

import com.github.promentor.data.access.UserAccess;
import com.github.promentor.mappers.UserMapper;
import com.github.promentor.rabbitmq.model.UserCreated;
import com.github.promentor.utils.IncomingMessageConverter;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class UserCreatedConsumer {

    private final UserAccess userAccess;

    private final UserMapper userMapper;

    public UserCreatedConsumer(UserAccess userAccess, UserMapper userMapper) {
        this.userAccess = userAccess;
        this.userMapper = userMapper;
    }

    @Incoming("user-created")
    public Uni<CompletionStage<Void>> consume(Message<byte[]> message) {
        Log.info("message reserved in \"user-created\"");

        JsonObject jsonPayload = IncomingMessageConverter.getMessageFromByteStream(message.getPayload());
        Log.debug("Reserved message: " + jsonPayload);

        UserCreated userCreated = jsonPayload.mapTo(UserCreated.class);

        return this.userAccess.createUser(this.userMapper.toUserDAO(userCreated))
                .onItem().transform(item -> message.ack());
    }
}
