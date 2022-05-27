package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByNif(String nif);
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
}