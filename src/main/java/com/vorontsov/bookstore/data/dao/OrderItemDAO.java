package com.vorontsov.bookstore.data.dao;

import com.vorontsov.bookstore.data.dto.OrderItemDto;

import java.util.List;

public interface OrderItemDAO {
    OrderItemDto create(OrderItemDto orderItemDto);

    List<OrderItemDto> getAll();

    List<OrderItemDto> findByOrderId(Long orderId);

    OrderItemDto findById(Long id);

    List<OrderItemDto> findByBookId(Long bookId);

    long countAll();

    OrderItemDto update(OrderItemDto orderItemDto);

    boolean deleteById(Long id);
}
