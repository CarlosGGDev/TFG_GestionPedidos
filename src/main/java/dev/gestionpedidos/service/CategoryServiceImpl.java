package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Optional<Category> getCategory(int categoryId) {
		return this.categoryRepository.findById(categoryId);
	}

	@Override
	public List<Category> getCategories() {
		return this.categoryRepository.findAll();
	}

	@Override
	public Optional<Category> saveCategory(Category category) {
		return Optional.of(this.categoryRepository.save(category));
	}

	@Override
	public Optional<Category> deleteCategory(int categoryId) {
		Optional<Category> categoryOpt = getCategory(categoryId);
		if (categoryOpt.isPresent()) {
			categoryRepository.deleteById(categoryId);
		}
		return categoryOpt;
	}
}
