package com.vorontsov.bookstore.service.mapper;

import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.entity.User;
import com.vorontsov.bookstore.service.dto.BookDto;
import com.vorontsov.bookstore.service.dto.UserDto;

public interface Mapper {
    BookDto mapToBookDto(Book book);

    Book mapToBook(BookDto bookDto);

    User mapToUser(UserDto userDto);

    UserDto mapToUserDto(User user);

}
