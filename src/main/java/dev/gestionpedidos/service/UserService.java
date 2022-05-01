package dev.gestionpedidos.service;

import dev.gestionpedidos.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
	List<User> getUsers();
	Optional<User> getUser(int userId);
	// TOREV: findByName es para poder recuperar los datos por nombre, para poder hacer
	//  la logica del metodo showMain en la clase MainController
	User findByNif(String nif);
	User findByName(String name);
	User findByEmail(String email);
	User saveUser(User user);
	Optional<User> deleteUser(int userId);
}
