package com.vorontsov.bookstore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet("/controller")
@Log4j2
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try{
            String commandParam = req.getParameter("command");
            Command command = CommandFactory.INSTANCE.get(commandParam);
            String page = command.process(req);
            req.getRequestDispatcher(page).forward(req, resp);
        }catch (Exception e){
            log.error(e);
            Command command = CommandFactory.INSTANCE.get("error");
            String page = command.process(req);
            req.setAttribute("exception", e);
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
