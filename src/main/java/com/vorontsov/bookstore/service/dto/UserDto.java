package com.vorontsov.bookstore.service.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
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
