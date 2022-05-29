package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product repository.
 * Access the database to persist products data.
 * Extends from the JpaInterface which includes methods to access the database
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
