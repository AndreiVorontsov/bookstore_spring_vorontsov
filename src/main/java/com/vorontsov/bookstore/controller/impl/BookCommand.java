package com.vorontsov.bookstore.controller.impl;


import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.service.ServiceBook;
import com.vorontsov.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;


@RequiredArgsConstructor
public class BookCommand implements Command {
    private final ServiceBook serviceBook;

    @Override
    public String process(HttpServletRequest req){
        long id = getId(req);
        BookDto book = serviceBook.getById(id);
        req.setAttribute("book", book);
        req.setAttribute("date", LocalDateTime.now());
        return "jsp/book/book.jsp";
    }

    private long getId(HttpServletRequest req) {
        String tmpId = req.getParameter("id");
        long id = Long.parseLong(tmpId);
        return id;
    }
}
