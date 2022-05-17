package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
