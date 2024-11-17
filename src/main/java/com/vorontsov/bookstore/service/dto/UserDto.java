package com.vorontsov.bookstore.service.dto;

import com.vorontsov.bookstore.data.entity.User;
import lombok.Data;

import java.util.Objects;

@Data
public class UserDto {
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
