package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registro") // http://localhost:8080/registro
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "public/signup";
    }

    @PostMapping(value = "/registro") // http://localhost:8080/registro
    public String signUp(@Valid @ModelAttribute User user,
                         BindingResult result,
                         Model model) {
        Optional<User> nameEntry = this.userService.findByName(user.getName());
        Optional<User> emailEntry = this.userService.findByEmail(user.getEmail());
        Optional<User> nifEntry = this.userService.findByNif(user.getNif());

        if (result.hasErrors()) {
            return "redirect:/registro";
        }
        if (nameEntry.isPresent()) {
            model.addAttribute("nameError", "El nombre ya existe");
        }
        if (emailEntry.isPresent()) {
            model.addAttribute("emailError", "El email ya existe");
        }
        if (nifEntry.isPresent()) {
            model.addAttribute("nifError", "El NIF ya existe");
        }
        if (nameEntry.isEmpty() && emailEntry.isEmpty() && nifEntry.isEmpty()){
            this.userService.saveUser(user);
            return "public/signupSuccess";
        }
        return "public/signup";
    }
}