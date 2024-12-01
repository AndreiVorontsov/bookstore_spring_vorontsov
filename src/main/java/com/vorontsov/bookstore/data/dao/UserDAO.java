package com.vorontsov.bookstore.data.dao;

import com.vorontsov.bookstore.data.dto.UserDto;
import com.vorontsov.bookstore.data.entity.User;

import java.util.List;

public interface UserDAO {

    UserDto create(UserDto userDto);

    List<UserDto> getAll();

    UserDto findByEmail(String email);

    UserDto findById(Long id);

    List<UserDto> findByLastName(String lastName);

    long countAll();

    UserDto update(UserDto userDto);

    boolean deleteByEmail(String email);
}
