package dev.gestionpedidos.service;

import dev.gestionpedidos.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
	List<User> getUsers();
	Optional<User> getUser(int userId);
	User saveUser(User user);
	Optional<User> deleteUser(int userId);
}
