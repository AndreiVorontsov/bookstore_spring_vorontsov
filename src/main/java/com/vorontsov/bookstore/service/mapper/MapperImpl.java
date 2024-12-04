package com.vorontsov.bookstore.service.mapper;

import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.entity.Order;
import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.service.dto.BookDto;
import com.vorontsov.bookstore.service.dto.OrderDto;
import com.vorontsov.bookstore.service.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl implements Mapper {

    @Override
    public BookDto mapToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setCover(BookDto.Cover.valueOf(String.valueOf(book.getCover())));
        bookDto.setPrice(book.getPrice());
        bookDto.setYearPublication(book.getYearPublication());
        bookDto.setDelete(book.isDelete());
        return bookDto;
    }

    @Override
    public Book mapToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setCover(Book.Cover.valueOf(bookDto.getCover().toString()));
        book.setPrice(bookDto.getPrice());
        book.setYearPublication(bookDto.getYearPublication());
        book.setDelete(bookDto.isDelete());
        return book;
    }

    @Override
    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setSurName(userDto.getSurName());
        user.setName(userDto.getSurName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(User.Role.valueOf(userDto.getRole().toString()));
        return user;
    }

    @Override
    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setSurName(user.getSurName());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(UserDto.Role.valueOf(user.getRole().toString()));
        return userDto;
    }

    @Override
    public Order mapToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setDate(orderDto.getDate());
        order.setUser(orderDto.getUser());
        order.setStatus(Order.Status.valueOf(orderDto.getStatus().toString()));
        order.setPrice(orderDto.getPrice());
        order.setOrderItems(orderDto.getOrderItems());
        return order;
    }

    @Override
    public OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setUser(order.getUser());
        orderDto.setStatus(OrderDto.Status.valueOf(order.getStatus().toString()));
        orderDto.setPrice(order.getPrice());
        orderDto.setOrderItems(order.getOrderItems());
        return orderDto;
    }
}
