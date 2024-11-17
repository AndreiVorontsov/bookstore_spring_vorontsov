package com.vorontsov.bookstore.controller.impl;

import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.data.config.ConfigPropertiesImpl;
import com.vorontsov.bookstore.data.connection.DataSource;
import com.vorontsov.bookstore.data.connection.DataSourceImpl;
import com.vorontsov.bookstore.data.dao.UserDAO;
import com.vorontsov.bookstore.data.dao.impl.UserDAOImpl;
import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.dto.UserDto;
import com.vorontsov.bookstore.service.impl.ServiceUserImpl;
import com.vorontsov.bookstore.service.mapper.Mapper;
import com.vorontsov.bookstore.service.mapper.MapperImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

@RequiredArgsConstructor
public class UsersCommand implements Command {
    private final ServiceUser serviceUser;

    @Override
    public String process(HttpServletRequest req) {
        List<UserDto> users = serviceUser.getAll();
        req.setAttribute("users", users);
        req.setAttribute("date", LocalDateTime.now());
        return "jsp/user/users.jsp";
    }
}
