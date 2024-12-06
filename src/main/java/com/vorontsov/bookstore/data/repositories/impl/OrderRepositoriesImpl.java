package com.vorontsov.bookstore.data.repositories.impl;


import com.vorontsov.bookstore.data.entity.Order;

import com.vorontsov.bookstore.data.repositories.OrderRepositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoriesImpl implements OrderRepositories {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findByUserId(Long user_id) {
        return null;
    }

    @Override
    public long countAll() {
        return 0;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

}
