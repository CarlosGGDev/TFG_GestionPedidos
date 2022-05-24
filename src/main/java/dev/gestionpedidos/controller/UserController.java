package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping // http://localhost:8080/usuarios
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(value = "/{userId}") // http://localhost:8080/usuarios/1
    public ResponseEntity<User> getUser(@PathVariable("userId") int userId) {
        Optional<User> userOpt = userService.getUser(userId);
        return ResponseEntity.of(userOpt);
    }

    @DeleteMapping(value = "/{userId}") // http://localhost:8080/usuarios/1
    public ResponseEntity<User> deleteUser(@PathVariable("userId") int userId) {
        Optional<User> userOpt = userService.deleteUser(userId);
        return ResponseEntity.of(userOpt);
    }

}
