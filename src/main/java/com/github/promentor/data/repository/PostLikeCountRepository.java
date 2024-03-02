package com.github.promentor.data.repository;

import com.github.promentor.data.domain.PostLikeCount;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostLikeCountRepository implements ReactivePanacheMongoRepository<PostLikeCount> {

}
