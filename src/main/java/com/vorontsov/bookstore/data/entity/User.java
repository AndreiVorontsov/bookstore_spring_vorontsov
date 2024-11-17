package com.vorontsov.bookstore.data.entity;

import lombok.Data;

@Data
public class User {
    private long id;
    private String surName;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    public enum Role {
        ADMIN,
        USER,
        MANAGER,
    }
}
