package com.vorontsov.bookstore.service.impl;

import com.vorontsov.bookstore.data.dao.BookDAO;
import com.vorontsov.bookstore.data.dao.impl.BookDAOJBDCImpl;
import com.vorontsov.bookstore.data.connection.DataSource;
import com.vorontsov.bookstore.service.ServiceBook;
import com.vorontsov.bookstore.service.dto.BookDto;
import com.vorontsov.bookstore.data.entity.Book;

import com.vorontsov.bookstore.service.mapper.Mapper;
import com.vorontsov.bookstore.service.mapper.MapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class ServiceBookImpl implements ServiceBook {
    private final BookDAO bookDAO;
    private final Mapper mapperImpl;



    @Override
    public List<BookDto> getAll() {
        log.debug("getAll");
        List<BookDto> booksDto = new ArrayList<>();
        bookDAO.getAll().forEach(book -> {
            BookDto bookDto = mapperImpl.mapToBookDto(book);
            booksDto.add(bookDto);
        });
        return booksDto;
    }

    @Override
    public BookDto getById(Long id) {
        log.debug("Get by Id" + id);
        Book book = bookDAO.getById(id);
        if (book == null) {
            log.error("No book with id:" + id);
            throw new RuntimeException("No book with id:" + id);
        }
        return mapperImpl.mapToBookDto(bookDAO.getById(id));
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        log.debug("Get by Isbn" + isbn);
        Book book = bookDAO.findByIsbn(isbn);
        if (book == null) {
           return null;
        }else{
            return mapperImpl.mapToBookDto(bookDAO.findByIsbn(isbn));
        }

    }


    @Override
    public BookDto create(BookDto bookDto) {
        log.debug("Create: " + bookDto);
        if (getByIsbn(bookDto.getIsbn()) == null){
            return mapperImpl.mapToBookDto(bookDAO.create(mapperImpl.mapToBook(bookDto)));
        } else{
            return null;
        }
    }

    @Override
    public BookDto update(BookDto bookDto) {
        log.debug("Update: " + bookDto);
        if(getByIsbn(bookDto.getIsbn()).getId() == bookDto.getId()) {
            return mapperImpl.mapToBookDto(bookDAO.update(mapperImpl.mapToBook(bookDto)));
        }else{ return null;}
    }


    @Override
    public void delete(long id) {
        log.debug("Delete" + id);
       boolean success = bookDAO.deleteById(id);
       if(!success){
           log.error("Couldn't delete book (id=" + id + ")");
           throw new RuntimeException("Couldn't delete book (id=" + id + ")");
       }
    }

    @Override
    public void softDelete(long id, boolean bool) {
        boolean success = bookDAO.softDeleteById(id,bool);
        if(!success){
            log.error("Couldn't softDelete book (id=" + id + ")");
            throw new RuntimeException("Couldn't softDelete book (id=" + id + ")");
        }
    }

    @Override
    public BigDecimal getTotalCostByAuthor(String author) {
        log.debug("getTotalCostByAuthor" + author);
        BigDecimal summ = new BigDecimal("0");
        for (Book book : bookDAO.findByAuthor(author)) {
            if (book.getPrice() != null) {
                summ = summ.add(book.getPrice());
            }else{
                log.debug("Not Price" + book);
            }
        }
        System.out.println(summ);
        return summ;
    }

}
