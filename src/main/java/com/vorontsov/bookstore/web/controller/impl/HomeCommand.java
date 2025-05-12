package com.vorontsov.bookstore.web.controller.impl;

import com.vorontsov.bookstore.service.dto.BookDto;
import com.vorontsov.bookstore.web.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/index")
public class HomeCommand {
//    return "index.jsp";

//    @Override
//    public String process(HttpServletRequest req) {
//        return "index.jsp";
//    }
}