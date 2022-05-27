package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
	Optional<Category> getCategory(int categoryId);
	Optional<List<Category>> getCategories();
	void saveCategory(Category category);
	void deleteCategory(int categoryId);
}
