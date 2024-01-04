package com.github.promentor.data.repository;

import com.github.promentor.data.domain.UserDAO;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements ReactivePanacheMongoRepository<UserDAO> {



}
