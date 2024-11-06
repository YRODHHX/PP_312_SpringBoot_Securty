package ru.uvuk.pp311.PP311.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.uvuk.pp311.PP311.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userProfile(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("user", userService.findUserByUsername(username));
        return "user/profile";
    }

}


