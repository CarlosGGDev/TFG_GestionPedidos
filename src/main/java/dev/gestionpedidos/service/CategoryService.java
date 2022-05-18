package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Category;
import java.util.List;

public interface CategoryService {
	List<Category> getCategories();
	Category saveCategory(Category category);
}
