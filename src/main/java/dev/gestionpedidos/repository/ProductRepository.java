package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Get all the products of a category
     * @param id Category ID
     * @return An optional list of products
     */
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    Optional<List<Product>> getProductsByCategory(int id);
}
