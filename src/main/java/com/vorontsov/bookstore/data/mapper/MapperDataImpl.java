package com.vorontsov.bookstore.data.mapper;

import com.vorontsov.bookstore.data.dto.BookDto;
import com.vorontsov.bookstore.data.dto.UserDto;
import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class MapperDataImpl implements Mapper {

    @Override
    public BookDto mapToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setCover(BookDto.Cover.valueOf(book.getCover().toString()));
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
}
