package com.vorontsov.bookstore.controller.impl.book;


import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.service.ServiceBook;
import com.vorontsov.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller("books")
@RequiredArgsConstructor
public class BooksCommand implements Command {
    private final ServiceBook serviceBook;

    @Override
    public String process(HttpServletRequest req) {

        List<BookDto> books = serviceBook.getAll();
        req.setAttribute("books", books);
        req.setAttribute("date", LocalDateTime.now());
        return "jsp/book/books.jsp";
    }
}
