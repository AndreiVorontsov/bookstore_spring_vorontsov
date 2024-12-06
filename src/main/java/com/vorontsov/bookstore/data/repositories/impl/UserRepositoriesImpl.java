package com.vorontsov.bookstore.data.repositories.impl;


import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.data.repositories.UserRepositories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoriesImpl implements UserRepositories {
    public static final String GET_ALL = "from User";

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery(GET_ALL, User.class).getResultList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return null;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return null;
    }

    @Override
    public long countAll() {
        return 0;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean deleteByEmail(String email) {
        return false;
    }

}
