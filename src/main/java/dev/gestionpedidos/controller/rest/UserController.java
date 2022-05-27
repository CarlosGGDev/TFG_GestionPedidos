package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(value = "/usuarios/{userId}") // http://localhost:8080/usuarios/1
    public void deleteUser(@PathVariable("userId") int userId) {
        this.userService.deleteUser(userId);
    }
}
