package com.vorontsov.bookstore.web.controller.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
       model.addAttribute("status",request.getAttribute("javax.servlet.error.status_code"));
       model.addAttribute("massage", request.getAttribute("javax.servlet.error.message"));
        return "error/error";
    }
}
