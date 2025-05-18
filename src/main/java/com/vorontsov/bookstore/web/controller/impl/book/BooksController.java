package com.vorontsov.bookstore.web.controller.impl.book;

import com.vorontsov.bookstore.service.ServiceBook;
import com.vorontsov.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BooksController {
    private final ServiceBook serviceBook;

    @GetMapping
    public String getBooks(Model model) {

        List<BookDto> books = serviceBook.getAll();
        model.addAttribute("books", books);
        model.addAttribute("date", LocalDateTime.now());
        return "book/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable long id, Model model) {
        BookDto bookDto = serviceBook.getById(id);

        model.addAttribute("book", bookDto);
        model.addAttribute("date", LocalDateTime.now());
        return "book/book";
    }
}
