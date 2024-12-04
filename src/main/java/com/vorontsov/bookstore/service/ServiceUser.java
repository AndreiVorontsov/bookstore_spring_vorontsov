package com.vorontsov.bookstore.service;

import com.vorontsov.bookstore.service.dto.UserCreateDto;
import com.vorontsov.bookstore.service.dto.UserDto;

import java.util.List;

public interface ServiceUser {

    UserDto create(UserCreateDto userCreateDtoDto);

    List<UserDto> getAll();

    UserDto getByEmail(String email);

    UserDto update(UserDto userDto);

    void delete(String email);

    UserDto login(String email, String password);
}
