package com.github.promentor.data.repository;

import com.github.promentor.data.domain.TagDAO;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class TagRepository implements ReactivePanacheMongoRepository<TagDAO> {

    public Uni<Optional<TagDAO>> findByKey(String key) {
        return find("key", key).firstResultOptional();
    }

}
