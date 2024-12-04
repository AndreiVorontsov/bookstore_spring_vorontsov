package com.vorontsov.bookstore.service.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String email;
    private String password;
}
