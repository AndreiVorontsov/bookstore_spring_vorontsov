package com.vorontsov.bookstore.data.repositories.impl;

import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.repositories.BookRepositories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookRepositoriesImpl implements BookRepositories {
    public static final String GET_ALL = "from Book";
    private static final String GET_BY_ISBN = "from Book where isbn = ?1";
    private static final String GET_BY_AUTHOR = "from Book where author = ?1";
    private static final String GET_COUNT_ALL = "count(*) from Book";

    @PersistenceContext
    private EntityManager manager;

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

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(manager.find(Book.class,id));
    }

    @Override
    public boolean deleteById(long id) {
        Book book = manager.find(Book.class,id);
        if (book != null){
            manager.remove(book);
            return true;
        }
            return false;
    }

    @Override
    public boolean softDeleteById(long id, boolean bool) {
        Book book = manager.find(Book.class,id);
        if (book != null){
            book.setDelete(bool);
            manager.merge(book);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(manager
                .createQuery(GET_BY_ISBN,Book.class)
                .setParameter(1,isbn)
                .getSingleResult());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return manager
                .createQuery(GET_BY_AUTHOR,Book.class)
                .setParameter(1,author)
                .getResultList();
    }

    @Override
    public long countAll() {
        return manager
                .createQuery(GET_COUNT_ALL,Long.class)
                .getSingleResult();
    }
}
