package com.vorontsov.bookstore.data.repositories.impl;

import com.vorontsov.bookstore.data.dao.UserDAO;
import com.vorontsov.bookstore.data.dto.UserDto;
import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.data.mapper.Mapper;
import com.vorontsov.bookstore.data.repositories.UserRepositories;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class UserRepositoriesImpl implements UserRepositories {
    private final UserDAO userDAO;
    private final Mapper mapperImpl;


    @Override
    public User create(User user) {
        UserDto userDto = mapperImpl.mapToUserDto(user);
        userDto = userDAO.create(userDto);
        return mapperImpl.mapToUser(userDto);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll()
                .stream()
                .map(mapperImpl::mapToUser)
                .toList();
    }

    @Override
    public User findByEmail(String email) {
        UserDto userDto = userDAO.findByEmail(email);
        if (userDto == null) {
            log.error(" UserRepositoriesImpl No user with email:" + email);
//            throw new RuntimeException("No user with email:" + email);
            return null;
        }
        return mapperImpl.mapToUser(userDto);
    }

    @Override
    public User findById(Long id) {
        UserDto userDto = userDAO.findById(id);
        return mapperImpl.mapToUser(userDto);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return userDAO.findByLastName(lastName)
                .stream()
                .map(mapperImpl::mapToUser)
                .toList();
    }

    @Override
    public long countAll() {

        return userDAO.countAll();
    }

    @Override
    public User update(User user) {
        UserDto userDto = mapperImpl.mapToUserDto(user);
        userDto = userDAO.update(userDto);
        return mapperImpl.mapToUser(userDto);
    }

    @Override
    public boolean deleteByEmail(String email) {

        return userDAO.deleteByEmail(email);
    }
}
