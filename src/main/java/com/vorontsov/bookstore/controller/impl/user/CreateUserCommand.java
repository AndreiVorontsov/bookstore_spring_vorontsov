package com.vorontsov.bookstore.controller.impl.user;

import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.dto.UserCreateDto;
import com.vorontsov.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller("create_user")
@RequiredArgsConstructor
public class CreateUserCommand implements Command {
    private final ServiceUser serviceUser;

    @Override
    public String process(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setEmail(email);
        userCreateDto.setPassword(password);

        UserDto user = serviceUser.create(userCreateDto);

        req.setAttribute("user", user);
        req.setAttribute("date", LocalDateTime.now());
        return "jsp/user/user.jsp";

    }
}
