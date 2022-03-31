package dev.gestionpedidos.controller;

import dev.gestionpedidos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/acceso")
public class SigninController {

    private final UserService userService;

    public SigninController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping // http://localhost:8080/acceso
    public String showSignInForm() {
        return "signin";
    }

    @PostMapping // http://localhost:8080/acceso
    public String signIn() {
        // TODO:
        return "null";
    }

}