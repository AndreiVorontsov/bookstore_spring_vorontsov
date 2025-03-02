package com.vorontsov.bookstore.web.controller;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String process(HttpServletRequest req);
}
