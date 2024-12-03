package com.vorontsov.bookstore.data.repositories;

import com.vorontsov.bookstore.data.entity.Order;

import java.util.List;

public interface OrderRepositories {
    Order create(Order order);

    List<Order> getAll();

    Order findById(Long id);

    List<Order> findByUserId(Long user_id);

    long countAll();

    Order update(Order order);
}
