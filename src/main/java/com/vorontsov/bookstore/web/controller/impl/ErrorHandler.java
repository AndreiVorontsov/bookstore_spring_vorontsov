package com.vorontsov.bookstore.web.controller.impl;

import com.vorontsov.bookstore.service.exception.AppException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class ErrorHandler {

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Model model, Exception e){
       log.error(e);
        return "error/error";
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAppException(Model model, AppException e){
        model.addAttribute("status", HttpStatus.NOT_FOUND);
        model.addAttribute("massage", e.getMessage());
        return "error/error";
    }
}

