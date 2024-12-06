package com.vorontsov.bookstore.data.repositories;

import com.vorontsov.bookstore.data.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositories {
    Order create(Order order);

    Order save (Order order);

    List<Order> getAll();

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long user_id);

    long countAll();

    Order update(Order order);
}
