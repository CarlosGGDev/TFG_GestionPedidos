package dev.gestionpedidos.service;

import dev.gestionpedidos.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
	Optional<List<User>> getUsers();
	Optional<User> getUser(int userId);
	Optional<User> findByNif(String nif);
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
	void saveUser(User user);
	void deleteUser(int userId);
}
