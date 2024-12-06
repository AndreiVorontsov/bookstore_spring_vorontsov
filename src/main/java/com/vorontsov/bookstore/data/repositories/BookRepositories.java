package com.vorontsov.bookstore.data.repositories;

import com.vorontsov.bookstore.data.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositories {
//    Book create(Book book);
//
    Book save (Book book);
//
    List<Book> getAll();
//
    Optional<Book> getById(long id);
//
//    Book update(Book book);
//
//    boolean deleteById(long id);
//
//    boolean softDeleteById(long id, boolean bool);
//
    Optional<Book> findByIsbn(String isbn);
//
//    List<Book> findByAuthor(String author);
//
//    long countAll();
}
