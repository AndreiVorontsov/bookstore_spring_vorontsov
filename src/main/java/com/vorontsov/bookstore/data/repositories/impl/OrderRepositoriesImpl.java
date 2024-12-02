package com.vorontsov.bookstore.data.repositories.impl;

import com.vorontsov.bookstore.data.dao.BookDAO;
import com.vorontsov.bookstore.data.dao.OrderDAO;
import com.vorontsov.bookstore.data.dao.OrderItemDAO;
import com.vorontsov.bookstore.data.dao.UserDAO;
import com.vorontsov.bookstore.data.dto.OrderDto;
import com.vorontsov.bookstore.data.dto.OrderItemDto;
import com.vorontsov.bookstore.data.dto.UserDto;
import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.entity.Order;
import com.vorontsov.bookstore.data.entity.OrderItem;
import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.data.mapper.DataMapper;
import com.vorontsov.bookstore.data.repositories.OrderRepositories;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderRepositoriesImpl implements OrderRepositories {
    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    private final UserDAO userDAO;
    private final BookDAO bookDAO;
    private final DataMapper dataMapper;


    @Override
    public Order create(Order order) {
        OrderDto orderDto = dataMapper.mapToOrderDto(order);
        orderDto = orderDAO.create(orderDto);

        OrderDto finalOrderDto = orderDto;
        order.getOrderItems().forEach(orderItem -> {
            OrderItemDto orderItemDto = dataMapper.mapToOrderItemDto(orderItem);
            orderItemDto.setOrder_id(finalOrderDto.getId());
            orderItemDAO.create(orderItemDto);
        });
        return findById(finalOrderDto.getId());
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll()
                .stream()
                .map(this::combineOrder)
                .collect(Collectors.toList());
    }

    @Override
    public Order findById(Long id) {
        OrderDto orderDto = orderDAO.findById(id);
        return combineOrder(orderDto);
    }

    @Override
    public long countAll() {
        return orderDAO.countAll();
    }

    @Override
    public Order update(Order order) {
        OrderDto orderDto = dataMapper.mapToOrderDto(order);
        orderDAO.update(orderDto);

        order.getOrderItems().forEach(orderItem -> {
            orderItemDAO.deleteById(orderItem.getId());
        });

        order.getOrderItems().forEach(orderItem -> {
            OrderItemDto orderItemDto = dataMapper.mapToOrderItemDto(orderItem);
            orderItemDto.setOrder_id(orderDto.getId());
            orderItemDAO.create(orderItemDto);
        });

        return findById(order.getId());
    }


    private Order combineOrder(OrderDto orderDto){
        Order order = dataMapper.mapToOrder(orderDto);

        Long userId = orderDto.getUser_id();
        UserDto userDto = userDAO.findById(userId);
        User user = dataMapper.mapToUser(userDto);
        order.setUser(user);

        List<OrderItemDto> orderItemDtos = orderItemDAO.findByOrderId(orderDto.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        orderItemDtos.forEach(orderItemDto -> {
            OrderItem orderItem = dataMapper.mapToOrderItem(orderItemDto);
            Book book = dataMapper.mapToBook(bookDAO.getById(orderItemDto.getBook_id()));
            orderItem.setBook(book);
            orderItems.add(orderItem);
        });
        order.setOrderItems(orderItems);
        return order;
    }

}
