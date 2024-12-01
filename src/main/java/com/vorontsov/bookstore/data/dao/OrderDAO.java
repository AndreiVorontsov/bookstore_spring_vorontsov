package com.vorontsov.bookstore.data.dao;

import com.vorontsov.bookstore.data.dto.OrderDto;

import java.util.List;

public interface OrderDAO {

    OrderDto create(OrderDto orderDto);

    List<OrderDto> getAll();

    List<OrderDto> findByStatus(String status);

    OrderDto findById(Long id);

    List<OrderDto> findByUserId(Long userId);

    long countAll();

    OrderDto update(OrderDto orderDto);

    boolean deleteById(Long id);
}
