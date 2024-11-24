package com.vorontsov.bookstore.data.dao;

import com.vorontsov.bookstore.data.entity.User;

import java.util.List;

public interface UserDAO {

    User create(User user);

    List<User> getAll();
    User findByEmail(String email);
    User findById(Long id);
    List<User> findByLastName(String lastName);
    long countAll();

    User update(User user);

    boolean deleteByEmail(String email);
}
