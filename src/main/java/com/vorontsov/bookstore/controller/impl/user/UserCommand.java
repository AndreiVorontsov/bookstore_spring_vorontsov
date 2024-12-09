package com.vorontsov.bookstore.controller.impl.user;


import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller("user")
@RequiredArgsConstructor
public class UserCommand implements Command {
    private final ServiceUser serviceUser;

    @Override
    public String process(HttpServletRequest req) {
        String email = req.getParameter("email");
        UserDto user = serviceUser.getByEmail(email);
        req.setAttribute("user", user);
        req.setAttribute("date", LocalDateTime.now());
        return "jsp/user/user.jsp";
    }
}
