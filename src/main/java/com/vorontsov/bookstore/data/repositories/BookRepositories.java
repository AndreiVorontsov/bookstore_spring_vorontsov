package com.vorontsov.bookstore.data.repositories;

import com.vorontsov.bookstore.data.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositories {
    Book save (Book book);

    List<Book> getAll();

    Optional<Book> getById(long id);

    boolean deleteById(long id);

    boolean softDeleteById(long id, boolean bool);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthor(String author);

    long countAll();
}
