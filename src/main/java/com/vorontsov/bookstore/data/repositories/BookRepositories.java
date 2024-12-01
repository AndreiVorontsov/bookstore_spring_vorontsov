package com.vorontsov.bookstore.data.repositories;

import com.vorontsov.bookstore.data.entity.Book;

import java.util.List;

public interface BookRepositories {
    Book create(Book book);

    List<Book> getAll();

    Book getById(long id);

    Book update(Book book);

    boolean deleteById(long id);

    boolean softDeleteById(long id, boolean bool);

    Book findByIsbn(String isbn);

    List<Book> findByAuthor(String author);

    long countAll();
}