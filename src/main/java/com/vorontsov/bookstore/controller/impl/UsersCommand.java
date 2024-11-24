package com.vorontsov.bookstore.controller.impl;

import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller("users")
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
