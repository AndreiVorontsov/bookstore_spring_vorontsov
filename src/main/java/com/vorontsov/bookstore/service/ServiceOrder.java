package com.vorontsov.bookstore.service;

import com.vorontsov.bookstore.service.dto.OrderDto;

import java.util.List;

public interface ServiceOrder {
    OrderDto create(OrderDto orderDto);

    List<OrderDto> getAll();

    OrderDto findById(Long id);

    List<OrderDto> findByUserId(Long user_id);

    long countAll();

    OrderDto update(OrderDto orderDto);
}
