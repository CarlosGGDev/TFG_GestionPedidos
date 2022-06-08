package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * CategoryService class test cases
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    public static final String NAME = "Name";
    public static final int ZERO = 0;
    public static final int ONE = 1;

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    Category category = Category.builder()
            .id(ONE)
            .name(NAME)
            .build();

    List<Category> categories = List.of(category);

    /**
     * Check that the service returns a category
     */
    @Test
    void should_return_one_category() {
        when(this.categoryRepository.findById(ONE)).thenReturn(Optional.of(this.category));

        Optional<Category> category = this.categoryService.getCategory(ONE);

        assertEquals(category.get(), this.category);
    }

    /**
     * Check that the service returns all categories
     */
    @Test
    void should_return_all_categories() {
        when(this.categoryRepository.findAll()).thenReturn(this.categories);

        Optional<List<Category>> categories = this.categoryService.getCategories();

        assertEquals(categories.get(), this.categories);
    }

    /**
     * Check that the service saves a category
     */
    @Test
    void should_save_one_category() {
        when(this.categoryRepository.save(this.category)).thenReturn(this.category);

        this.categoryService.saveCategory(this.category);

        verify(this.categoryRepository, times(1)).save(this.category);
    }

    /**
     * Check that the service deletes a category
     */
    @Test
    void should_delete_category() {
        when(this.categoryService.getCategory(ONE)).thenReturn(Optional.of(this.category));
        doNothing().when(this.categoryRepository).deleteById(ONE);

        this.categoryService.deleteCategory(ONE);

        verify(this.categoryRepository, times(1)).deleteById(ONE);
    }

}