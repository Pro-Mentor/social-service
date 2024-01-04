package com.github.promentor.data.access;

import com.github.promentor.data.domain.UserDAO;
import com.github.promentor.data.repository.UserRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserAccess {

    private final UserRepository userRepository;

    public UserAccess(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Uni<UserDAO> createUser(UserDAO userDAO) {
        return this.userRepository.persist(userDAO);
    }
}
