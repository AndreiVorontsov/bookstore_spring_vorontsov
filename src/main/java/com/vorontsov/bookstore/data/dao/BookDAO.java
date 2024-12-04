package com.vorontsov.bookstore.data.dao;

import com.vorontsov.bookstore.data.dto.BookDto;

import java.util.List;


public interface BookDAO {
    BookDto create(BookDto bookDto);

    List<BookDto> getAll();

    BookDto getById(long id);

    BookDto update(BookDto bookDto);

    boolean deleteById(long id);

    boolean softDeleteById(long id, boolean bool);

    BookDto findByIsbn(String isbn);

    List<BookDto> findByAuthor(String author);

    long countAll();

}