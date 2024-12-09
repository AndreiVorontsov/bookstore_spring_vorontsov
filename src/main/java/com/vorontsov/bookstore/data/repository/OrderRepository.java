package com.vorontsov.bookstore.data.repository;

import com.vorontsov.bookstore.data.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    List<Order> getAll();

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long user_id);

    long countAll();
}
