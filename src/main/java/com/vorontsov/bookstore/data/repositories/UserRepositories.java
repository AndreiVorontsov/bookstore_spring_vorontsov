package com.vorontsov.bookstore.data.repositories;

import com.vorontsov.bookstore.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositories {

    User save(User user);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findByLastName(String lastName);

    long countAll();

    boolean deleteById(long id);

    boolean deleteByEmail(String email);
}
