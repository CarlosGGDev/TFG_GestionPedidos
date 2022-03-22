package dev.gestionpedidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninController {

    @GetMapping(value = "/")
    public String signIn() {
        return "signin";
    }

}