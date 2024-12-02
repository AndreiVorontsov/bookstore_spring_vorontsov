package com.vorontsov.bookstore.data.repositories.impl;

import com.vorontsov.bookstore.data.dao.BookDAO;
import com.vorontsov.bookstore.data.dto.BookDto;
import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.mapper.DataMapper;
import com.vorontsov.bookstore.data.repositories.BookRepositories;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class BookRepositoriesImpl implements BookRepositories {
    private final BookDAO bookDAO;
    private final DataMapper dataMapperImpl;

    @Override
    public Book create(Book book) {
        BookDto bookDto = dataMapperImpl.mapToBookDto(book);
        bookDto = bookDAO.create(bookDto);
        return dataMapperImpl.mapToBook(bookDto);
    }

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll()
                .stream()
                .map(dataMapperImpl::mapToBook)
                .toList();
    }

    @Override
    public Book getById(long id) {
        BookDto bookDto = bookDAO.getById(id);
        return dataMapperImpl.mapToBook(bookDto);
    }

    @Override
    public Book update(Book book) {
        BookDto bookDto = dataMapperImpl.mapToBookDto(book);
        bookDto = bookDAO.update(bookDto);
        return dataMapperImpl.mapToBook(bookDto);
    }

    @Override
    public boolean deleteById(long id) {
        return bookDAO.deleteById(id);
    }

    @Override
    public boolean softDeleteById(long id, boolean bool) {
        return bookDAO.softDeleteById(id, bool);
    }

    @Override
    public Book findByIsbn(String isbn) {
        BookDto bookDto = bookDAO.findByIsbn(isbn);
        return dataMapperImpl.mapToBook(bookDto);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookDAO.findByAuthor(author)
                .stream()
                .map(dataMapperImpl::mapToBook)
                .toList();
    }

    @Override
    public long countAll() {
        return bookDAO.countAll();
    }
}
