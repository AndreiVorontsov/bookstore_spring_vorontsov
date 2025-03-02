package com.vorontsov.bookstore.web.controller.impl;

import com.vorontsov.bookstore.web.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("error")
@RequiredArgsConstructor
public class ErrorCommand implements Command {
    @Override
    public String process(HttpServletRequest req) {
        req.setAttribute("error_command", req.getQueryString());
        return "jsp/error/error.jsp";
    }
}

