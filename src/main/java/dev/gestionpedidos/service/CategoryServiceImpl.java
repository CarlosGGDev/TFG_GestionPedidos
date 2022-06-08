package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Category service implementation.
 * The Category repository is injected as dependency at controller level.
 * Methods invoke the repository to persist data.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Get a category by id
	 * @param categoryId Category id
	 * @return Optional of Category
	 */
	@Override
	public Optional<Category> getCategory(int categoryId) {
		return this.categoryRepository.findById(categoryId);
	}

	/**
	 * Get all categories
	 * @return Optional of categories list
	 */
	@Override
	public Optional<List<Category>> getCategories() {
		return Optional.of(this.categoryRepository.findAll());
	}

	/**
	 * Saves a category
	 * @param category Category to be saved
	 */
	@Override
	public void saveCategory(Category category) {
		this.categoryRepository.save(category);
	}

	/**
	 * Deletes category.
	 * @param categoryId Id of category to be deleted
	 */
	@Override
	public void deleteCategory(int categoryId) {
		Optional<Category> categoryOpt = this.getCategory(categoryId);
		if (categoryOpt.isPresent()) {
			this.categoryRepository.deleteById(categoryId);
		}
	}
}
