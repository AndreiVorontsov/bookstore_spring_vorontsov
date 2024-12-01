package com.vorontsov.bookstore.data.mapper;

import com.vorontsov.bookstore.data.dto.BookDto;
import com.vorontsov.bookstore.data.dto.UserDto;
import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.entity.User;

public interface Mapper {


    BookDto mapToBookDto(Book book);

    Book mapToBook(BookDto bookDto);

    User mapToUser(UserDto userDto);

    UserDto mapToUserDto(User user);


}
