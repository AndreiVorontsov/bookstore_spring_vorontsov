package com.vorontsov.bookstore.service.impl;

import com.vorontsov.bookstore.data.entity.Order;
import com.vorontsov.bookstore.data.repositories.OrderRepositories;
import com.vorontsov.bookstore.service.ServiceOrder;
import com.vorontsov.bookstore.service.dto.OrderDto;
import com.vorontsov.bookstore.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceOrderImpl implements ServiceOrder {
    private final OrderRepositories orderRepositories;
    private final Mapper mapperImpl;

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = mapperImpl.mapToOrder(orderDto);
        order = orderRepositories.create(order);
        orderDto = mapperImpl.mapToOrderDto(order);

        return orderDto;
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepositories.getAll()
                .stream()
                .map(mapperImpl::mapToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepositories.findById(id);
        OrderDto orderDto = mapperImpl.mapToOrderDto(order);
        return orderDto;
    }

    @Override
    public long countAll() {
        return orderRepositories.countAll();
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Order order = mapperImpl.mapToOrder(orderDto);
        order = orderRepositories.update(order);
        orderDto = mapperImpl.mapToOrderDto(order);
        return orderDto;
    }
}
