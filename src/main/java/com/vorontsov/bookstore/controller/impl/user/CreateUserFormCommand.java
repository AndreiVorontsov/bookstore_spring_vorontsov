package com.vorontsov.bookstore.controller.impl.user;

import com.vorontsov.bookstore.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("create_user_form")
@RequiredArgsConstructor
public class CreateUserFormCommand implements Command {

    @Override
    public String process(HttpServletRequest req) {
        return "jsp/user/create.jsp";
    }
}
