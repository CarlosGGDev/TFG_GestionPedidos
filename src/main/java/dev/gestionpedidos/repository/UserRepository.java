package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository.
 * Access the database to persist users data.
 * Extends from the JpaInterface which includes methods to access the database.
 * In addition, custom methods are created for certain requirements following
 * the Spring syntax which automatically recognizes the query from the method name.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByNif(String nif);
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
}