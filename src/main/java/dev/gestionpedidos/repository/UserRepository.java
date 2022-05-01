package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByNif(String nif);
	User findByName(String name);
	User findByEmail(String email);
}
