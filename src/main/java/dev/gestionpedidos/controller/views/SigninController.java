package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninController {

    @GetMapping(value = "/acceso") // http://localhost:8080/acceso
    public String showSignInForm(Model model) {
        model.addAttribute("user", new User());
        return "public/signin";
    }
}