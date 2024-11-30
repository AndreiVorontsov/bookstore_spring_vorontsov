package com.vorontsov.bookstore.data.repositories.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String surName;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private com.vorontsov.bookstore.service.dto.UserDto.Role role;

    public enum Role {
        ADMIN,
        USER,
        MANAGER,
    }
}
