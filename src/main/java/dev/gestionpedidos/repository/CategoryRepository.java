package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Category repository.
 * Access the database to persist categories data.
 * Extends from the JpaInterface which includes methods to access the database
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
