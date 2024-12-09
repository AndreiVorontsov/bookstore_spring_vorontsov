package com.vorontsov.bookstore.service.impl;

import com.vorontsov.bookstore.data.entity.Order;
import com.vorontsov.bookstore.data.repository.OrderRepository;
import com.vorontsov.bookstore.service.ServiceOrder;
import com.vorontsov.bookstore.service.dto.OrderDto;
import com.vorontsov.bookstore.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceOrderImpl implements ServiceOrder {
    private final OrderRepository orderRepository;
    private final Mapper mapperImpl;

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = mapperImpl.mapToOrder(orderDto);
        order = orderRepository.save(order);
        orderDto = mapperImpl.mapToOrderDto(order);

        return orderDto;
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.getAll()
                .stream()
                .map(mapperImpl::mapToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Long id) {
        Optional<Order> box = orderRepository.findById(id);
        Order order = box.orElseThrow();
        OrderDto orderDto = mapperImpl.mapToOrderDto(order);
        return orderDto;
    }

    @Override
    public long countAll() {
        return orderRepository.countAll();
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Order order = mapperImpl.mapToOrder(orderDto);
        order = orderRepository.save(order);
        orderDto = mapperImpl.mapToOrderDto(order);
        return orderDto;
    }

    @Override
    public List<OrderDto> findByUserId(Long user_id) {
        return orderRepository.findByUserId(user_id)
                .stream()
                .map(mapperImpl::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
