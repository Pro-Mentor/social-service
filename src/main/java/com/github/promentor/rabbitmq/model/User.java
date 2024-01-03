package com.github.promentor.rabbitmq.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class User {

    public String username;
    public String email;
    public String temparyPassword;
    public String firstName;
    public String lastName;
    public String tenantId;

    public User() {
    }

    public User(String username, String email, String temparyPassword, String firstName, String lastName, String tenantId) {
        this.username = username;
        this.email = email;
        this.temparyPassword = temparyPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", temparyPassword='" + temparyPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
