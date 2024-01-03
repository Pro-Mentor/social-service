package com.github.promentor.data.repository;

import com.github.promentor.data.domain.UserDAO;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<UserDAO> {



}
