package com.vorontsov.bookstore.web.controller.impl.user;


import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.dto.UserCreateDto;
import com.vorontsov.bookstore.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final ServiceUser serviceUser;

    @GetMapping("/{email}")
    public String getUser(@PathVariable String email, Model model) {
        UserDto user = serviceUser.getByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("date", LocalDateTime.now());
        return "user/user";
    }

    @GetMapping
    public String getUsers(Model model) {
        List<UserDto> users = serviceUser.getAll();
        model.addAttribute("users", users);
        model.addAttribute("date", LocalDateTime.now());
        return "user/users";
    }

    @GetMapping("/create")
    public String createUserForm() {
        return "user/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserCreateDto user) {
        serviceUser.create(user);
        return "redirect:/users/" + user.getEmail();
    }

}



