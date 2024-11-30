package com.vorontsov.bookstore.service;

import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.service.dto.UserDto;

import java.util.List;

public interface ServiceUser {

    UserDto create(UserDto userDto);

    List<UserDto> getAll();

    UserDto getByEmail(String email);

    UserDto update(UserDto userDto);

    void delete(String email);

    User login(String email, String password);
}
