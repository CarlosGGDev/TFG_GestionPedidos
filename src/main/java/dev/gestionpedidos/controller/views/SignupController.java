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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/registro")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping // http://localhost:8080/registro
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "public/signup";
    }

    @PostMapping // http://localhost:8080/registro
    public String signUp(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        User nameEntry = userService.findByName(user.getName());
        User emailEntry = userService.findByEmail(user.getEmail());
        User nifEntry = userService.findByNif(user.getNif());

        if (result.hasErrors()) {
            return "redirect:/registro";
        }
        if (nameEntry != null) {
            model.addAttribute("nameError", "El nombre ya existe");
        }
        if (emailEntry != null) {
            model.addAttribute("emailError", "El email ya existe");
        }
        if (nifEntry != null) {
            model.addAttribute("nifError", "El NIF ya existe");
        }
        if (nameEntry == null && emailEntry == null && nifEntry == null){
            userService.saveUser(user);
            return "public/signupSuccess";
        }
        return "public/signup";
    }
}