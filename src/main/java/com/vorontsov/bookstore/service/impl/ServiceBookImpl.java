package com.vorontsov.bookstore.service.impl;

import com.vorontsov.bookstore.data.entity.Book;
import com.vorontsov.bookstore.data.repository.BookRepository;
import com.vorontsov.bookstore.service.ServiceBook;
import com.vorontsov.bookstore.service.dto.BookDto;
import com.vorontsov.bookstore.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceBookImpl implements ServiceBook {
    private final BookRepository bookRepository;
    private final Mapper mapperImpl;

    @Override
    public List<BookDto> getAll() {
        log.debug("getAll");
        List<BookDto> booksDto = new ArrayList<>();
        bookRepository.getAll().forEach(book -> {
            BookDto bookDto = mapperImpl.mapToBookDto(book);
            booksDto.add(bookDto);
        });
        return booksDto;
    }

    @Override
    public BookDto getById(Long id) {
        log.debug("Get by Id" + id);
        Optional<Book> box = bookRepository.getById(id);
        Book book = box.orElseThrow();
        return mapperImpl.mapToBookDto(book);
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        log.debug("Get by Isbn" + isbn);
        Optional<Book> box = bookRepository.findByIsbn(isbn);
        Book book = box.orElseThrow();
        return mapperImpl.mapToBookDto(book);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        log.debug("Create: " + bookDto);
        if (getByIsbn(bookDto.getIsbn()) == null) {
            Book book = mapperImpl.mapToBook(bookDto);
            book = bookRepository.save(book);
            return mapperImpl.mapToBookDto(book);
        } else {
            return null;
        }
    }

    @Override
    public BookDto update(BookDto bookDto) {
        log.debug("Update: " + bookDto);
        if (getByIsbn(bookDto.getIsbn()).getId().equals(bookDto.getId())) {
            Book book = mapperImpl.mapToBook(bookDto);
            book = bookRepository.save(book);
            return mapperImpl.mapToBookDto(book);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        log.debug("Delete" + id);
        boolean success = bookRepository.deleteById(id);
        if (!success) {
            log.error("Couldn't delete book (id=" + id + ")");
            throw new RuntimeException("Couldn't delete book (id=" + id + ")");
        }
    }

    @Override
    public void softDelete(long id, boolean bool) {
        boolean success = bookRepository.softDeleteById(id, bool);
        if (!success) {
            log.error("Couldn't softDelete book (id=" + id + ")");
            throw new RuntimeException("Couldn't softDelete book (id=" + id + ")");
        }
    }

    @Override
    public BigDecimal getTotalCostByAuthor(String author) {
        log.debug("getTotalCostByAuthor" + author);
        BigDecimal summ = new BigDecimal("0");
        for (Book book : bookRepository.findByAuthor(author)) {
            if (book.getPrice() != null) {
                summ = summ.add(book.getPrice());
            } else {
                log.debug("Not Price" + book);
            }
        }
        System.out.println(summ);
        return summ;
    }

}
