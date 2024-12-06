package com.vorontsov.bookstore.data.repositories.impl;

import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.repositories.BookRepositories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookRepositoriesImpl implements BookRepositories {

    public static final String GET_ALL = "from Book";

    @PersistenceContext
    private EntityManager manager;

//    @Override
//    public Book create(Book book) {
//        return null;
//    }
//
    @Override
    public Book save(Book book) {
        if (book.getId() != null){
            manager.merge(book);
        } else {
            manager.persist(book);
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        return manager.createQuery(GET_ALL, Book.class).getResultList();
    }
//
    @Override
    public Optional<Book> getById(long id) {

        return Optional.ofNullable(manager.find(Book.class,id));
    }
//
//    @Override
//    public Book update(Book book) {
//        return null;
//    }
//
//    @Override
//    public boolean deleteById(long id) {
//        return false;
//    }
//
//    @Override
//    public boolean softDeleteById(long id, boolean bool) {
//        return false;
//    }
//
    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(manager.find(Book.class,isbn));
    }
//
//    @Override
//    public List<Book> findByAuthor(String author) {
//        return null;
//    }
//
//    @Override
//    public long countAll() {
//        return 0;
//    }


}
