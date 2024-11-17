package com.vorontsov.bookstore.controller.impl;

import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.service.ServiceBook;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class HomeCommand implements Command{
    @Override
    public String process(HttpServletRequest req) {
        return "index.jsp";
    }
}