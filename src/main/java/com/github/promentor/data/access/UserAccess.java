package com.github.promentor.data.access;

import com.github.promentor.data.domain.UserDAO;
import com.github.promentor.data.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserAccess {

    private final UserRepository userRepository;

    public UserAccess(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDAO userDAO) {
        this.userRepository.persist(userDAO);
    }
}
