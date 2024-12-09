package com.vorontsov.bookstore.data.repository.impl;


import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.data.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    public static final String GET_ALL = "from User";
    public static final String GET_LAST_NAME = "from User where lastName = ?1";
    private static final String GET_COUNT_ALL = "count(*) from User";
    private static final String GET_BY_EMAIL = "from User where email = ?1";

    @PersistenceContext
    private EntityManager manager;


    @Override
    public User save(User user) {
        if (user.getId() != null) {
            manager.merge(user);
        } else {
            manager.persist(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {

        return manager
                .createQuery(GET_ALL, User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(manager
                .createQuery(GET_BY_EMAIL, User.class)
                .setParameter(1, email)
                .getSingleResult());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(manager.find(User.class, id));
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return manager
                .createQuery(GET_LAST_NAME, User.class)
                .setParameter(1, lastName)
                .getResultList();
    }

    @Override
    public long countAll() {
        return manager
                .createQuery(GET_COUNT_ALL, Long.class)
                .getSingleResult();
    }

    @Override
    public boolean deleteById(long id) {
        User user = manager.find(User.class, id);
        if (user != null) {
            manager.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByEmail(String email) {
        Optional<User> box = findByEmail(email);
        User user = box.orElse(null);
        if (user != null) {
            manager.remove(user);
            return true;
        }
        return false;
    }
}
