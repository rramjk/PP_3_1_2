package ru.ramazanov.CRUD_User_App.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ramazanov.CRUD_User_App.models.User;
import ru.ramazanov.CRUD_User_App.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("usersList", service.getUsers());
        return "index";
    }
    @GetMapping("/show")
    public String showPerson(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (service.getUserById(id).isEmpty()) {
            return "exception";
        }
        model.addAttribute("user", service.getUserById(id).get());
        return "show";
    }
    @PostMapping("/delete")
    public String deletePerson(@RequestParam(value = "id", required = false) Integer id) {
        if (service.getUserById(id).isEmpty()) {
            return "exception";
        }
        service.deleteUserById(id);
        return "redirect: /users";
    }

    @GetMapping("/edit")
    public String showCreatePage(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (service.getUserById(id).isEmpty()) {
            return "exception";
        }
        model.addAttribute("user", service.getUserById(id).get());
        return "edit";
    }
    @PostMapping("/edit")
    public String editPerson(@RequestParam(value = "id", required = false) Integer id,
                             @ModelAttribute("user") @Valid User user, BindingResult result) {
        if (service.getUserById(id).isEmpty()) {
            return "exception";
        }
        if(result.hasErrors()) {
            return "edit";
        }
        service.updateUserById(id, user);
        return "redirect: /users/show?id="+id;
    }

    @GetMapping("/create")
    public String showCreatePage(@ModelAttribute("user") User user) {
        return "create";
    }

    @PostMapping("/create")
    public String createPerson(@ModelAttribute("user") @Valid User user,
                               BindingResult result) {
        if(result.hasErrors()) {
            return "create";
        }
        service.addUser(user);
        return "redirect: /users";
    }



}
