package com.vorontsov.bookstore.data.mapper;

import com.vorontsov.bookstore.data.dto.BookDto;
import com.vorontsov.bookstore.data.dto.OrderDto;
import com.vorontsov.bookstore.data.dto.OrderItemDto;
import com.vorontsov.bookstore.data.dto.UserDto;
import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.entity.Order;
import com.vorontsov.bookstore.data.entity.OrderItem;
import com.vorontsov.bookstore.data.entity.User;

public interface DataMapper {
    BookDto mapToBookDto(Book book);

    Book mapToBook(BookDto bookDto);

    User mapToUser(UserDto userDto);

    UserDto mapToUserDto(User user);

    Order mapToOrder(OrderDto orderDto);

    OrderDto mapToOrderDto(Order order);

    OrderItem mapToOrderItem(OrderItemDto orderItemDto);

    OrderItemDto mapToOrderItemDto(OrderItem orderItem);
}
