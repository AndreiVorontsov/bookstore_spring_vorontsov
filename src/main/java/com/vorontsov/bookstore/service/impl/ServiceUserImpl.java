package com.vorontsov.bookstore.service.impl;

import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.data.repository.UserRepository;
import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.dto.UserCreateDto;
import com.vorontsov.bookstore.service.dto.UserDto;
import com.vorontsov.bookstore.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceUserImpl implements ServiceUser {
    private final UserRepository userRepositoryImpl;
    private final Mapper mapperImpl;


    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userCreateDto.getEmail());
        userDto.setPassword(userCreateDto.getPassword());
        userDto.setRole(UserDto.Role.USER);

        log.debug("Create {}", userDto);
        User user = mapperImpl.mapToUser(userDto);
        user = userRepositoryImpl.save(user);
        return mapperImpl.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        log.debug("Get All");
        return userRepositoryImpl.findAll()
                .stream()
                .map(mapperImpl::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        Optional<User> box = userRepositoryImpl.findByEmail(email);
        User user = box.orElseThrow();
        return mapperImpl.mapToUserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Update {}", userDto);
        User user = mapperImpl.mapToUser(userDto);
        user = userRepositoryImpl.save(user);
        return mapperImpl.mapToUserDto(user);
    }

    @Override
    public void delete(String email) {
        log.debug("Delete {}", email);
        boolean success = userRepositoryImpl.deleteByEmail(email);
        if (!success) {
            throw new RuntimeException("Couldn't delete user (email=" + email + ")");
        }
    }

    @Override
    public UserDto login(String email, String password) {
        log.debug("Get User with email = {} password = {}", email, password);
        Optional<User> box = userRepositoryImpl.findByEmail(email);
        User user = box.orElseThrow();
        if (user.getPassword().equals(password)) {
            return mapperImpl.mapToUserDto(user);
        } else {
            throw new RuntimeException("Incorrect password: " + password);
        }
    }
}
