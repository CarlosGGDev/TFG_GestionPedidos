package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninController {

    private final UserService userService;

    public SigninController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/acceso") // http://localhost:8080/acceso
    public String showSignInForm(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }
}