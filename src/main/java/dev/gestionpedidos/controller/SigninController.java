package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/acceso")
public class SigninController {

    private final UserService userService;

    public SigninController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping // http://localhost:8080/acceso
    public String showSignInForm(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }

    @PostMapping // http://localhost:8080/acceso
    public String signIn(@ModelAttribute User user) {
        // TODO: es necesario? ya que una vez el acceso es correcto, redirige a main, no pasa por este controlador
        return "null";
    }
}