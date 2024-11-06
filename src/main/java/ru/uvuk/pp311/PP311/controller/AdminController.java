package ru.uvuk.pp311.PP311.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.uvuk.pp311.PP311.model.User;
import ru.uvuk.pp311.PP311.service.RoleService;
import ru.uvuk.pp311.PP311.service.RoleServiceImpl;
import ru.uvuk.pp311.PP311.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.showAllUsers());
        return "admin/user-list";
    }


    @GetMapping("/new_user")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new_user";
    }
    @PostMapping
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/edit_panel/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/edit_user";
    }

    @PostMapping("/edit_user")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete_user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
