package com.vorontsov.bookstore.service.impl;

import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.data.repositories.UserRepositories;
import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.dto.UserDto;
import com.vorontsov.bookstore.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceUserImpl implements ServiceUser {
    private final UserRepositories userRepositoriesImpl;
    private final Mapper mapperImpl;


    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Create" + userDto);
        User user = mapperImpl.mapToUser(userDto);
        user = userRepositoriesImpl.create(user);
        return mapperImpl.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        log.debug("Get All");
        return userRepositoriesImpl.getAll()
                .stream()
                .map(mapperImpl::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        log.debug("Get by Email" + email);
        User user = userRepositoriesImpl.findByEmail(email);
        if (user == null) {
            log.error("No user with email:" + email);
//            throw new RuntimeException("No user with email:" + email);
            return null;
        }
        return mapperImpl.mapToUserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Update" + userDto);
        User user = mapperImpl.mapToUser(userDto);
        user = userRepositoriesImpl.update(user);
        return mapperImpl.mapToUserDto(user);
    }

    @Override
    public void delete(String email) {
        log.debug("Delete" + email);
        boolean success = userRepositoriesImpl.deleteByEmail(email);
        if (!success) {
            log.error("Couldn't delete user (email=" + email + ")");
            throw new RuntimeException("Couldn't delete user (email=" + email + ")");
        }
    }

    @Override
    public User login(String email, String password) {
        log.debug("Get User with email = " + email + "password = " + password);
        User user = new User();
        user = userRepositoriesImpl.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                log.debug("Incorrect password" + password);
            }
        } else {
            log.debug("No user with email:" + email);
        }
        return null;
    }
}
