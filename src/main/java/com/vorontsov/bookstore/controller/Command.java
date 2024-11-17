package com.vorontsov.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String process(HttpServletRequest req);
}
