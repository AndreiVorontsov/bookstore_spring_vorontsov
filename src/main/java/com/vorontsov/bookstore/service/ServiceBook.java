package com.vorontsov.bookstore.service;

import com.vorontsov.bookstore.service.dto.BookDto;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceBook {

    List<BookDto> getAll();

    BookDto getById(Long id);
    BookDto getByIsbn(String isbn);

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    void delete(long id);

    void softDelete(long id, boolean bool);

    public BigDecimal getTotalCostByAuthor(String author);
}
