package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller to manage requests of users
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * DELETE controller for delete a user. When this URL receives a request, the service deletes a user
     * @param userId The id of the user to be deleted
     */
    @DeleteMapping(value = "/usuarios/{userId}") // http://localhost:8080/usuarios/1
    public void deleteUser(@PathVariable("userId") int userId) {
        this.userService.deleteUser(userId);
    }
}
