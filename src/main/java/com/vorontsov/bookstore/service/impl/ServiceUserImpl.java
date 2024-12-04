package com.vorontsov.bookstore.service.impl;

import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.data.repositories.UserRepositories;
import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.dto.UserCreateDto;
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
    public UserDto create(UserCreateDto userCreateDto) {
        UserDto userDto = new UserDto();
//        userDto = getByEmail(userCreateDto.getEmail());
//        if (userDto != null) {
//            throw new RuntimeException("exists user with email:" + userCreateDto.getEmail());
//        }
//        if (userDto.getEmail().equals(userCreateDto.getEmail())){
//            throw new RuntimeException("exists user with email:" + userCreateDto.getEmail());
//        }
        userDto.setEmail(userCreateDto.getEmail());
        userDto.setPassword(userCreateDto.getPassword());
        userDto.setRole(UserDto.Role.USER);

        log.debug("Create {}", userDto);
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
        User user = userRepositoriesImpl.findByEmail(email);
        return mapperImpl.mapToUserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Update {}", userDto);
        User user = mapperImpl.mapToUser(userDto);
        user = userRepositoriesImpl.update(user);
        return mapperImpl.mapToUserDto(user);
    }

    @Override
    public void delete(String email) {
        log.debug("Delete {}", email);
        boolean success = userRepositoriesImpl.deleteByEmail(email);
        if (!success) {
            throw new RuntimeException("Couldn't delete user (email=" + email + ")");
        }
    }

    @Override
    public UserDto login(String email, String password) {
        log.debug("Get User with email = {} password = {}", email, password);
        User user = userRepositoriesImpl.findByEmail(email);
        if (user.getPassword().equals(password)) {
            return mapperImpl.mapToUserDto(user);
        } else {
            throw new RuntimeException("Incorrect password: " + password);
        }
    }
}
