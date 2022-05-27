package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Category;
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
	public Optional<List<Category>> getCategories() {
		return Optional.of(this.categoryRepository.findAll());
	}

	@Override
	public void saveCategory(Category category) {
		this.categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Optional<Category> categoryOpt = getCategory(categoryId);
		if (categoryOpt.isPresent()) {
			this.categoryRepository.deleteById(categoryId);
		}
	}
}
