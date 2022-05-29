package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    public static final String NAME = "Name";
    public static final int ONE = 1;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryService categoryService;

    Category categoryDummy = Category.builder()
            .id(ONE)
            .name(NAME)
            .build();

    List<Category> categoriesDummy = List.of(categoryDummy);

    @Test
    void should_return_all_categories() {
        when(this.categoryService.getCategories()).thenReturn(Optional.of(this.categoriesDummy));
        List<Category> categories = this.categoryService.getCategories().get();

        assertThat(categories).isNotNull();
        assertEquals(this.categoriesDummy, categories);
    }

    @Test
    void should_return_one_category() {
        when(this.categoryService.getCategory(anyInt())).thenReturn(Optional.of(this.categoryDummy));
        Category category = this.categoryService.getCategory(anyInt()).get();

        assertThat(category).isNotNull();
        assertEquals(this.categoryDummy, category);
    }

    @Test
    void should_save_category() {
        doNothing().when(this.categoryService).saveCategory(this.categoryDummy);
        this.categoryService.saveCategory(this.categoryDummy);
        verify(this.categoryService, times(ONE)).saveCategory(this.categoryDummy);
    }

    @Test
    void should_delete_category() {
        doNothing().when(this.categoryService).deleteCategory(ONE);
        this.categoryService.deleteCategory(ONE);
        verify(this.categoryService, times(ONE)).deleteCategory(ONE);
    }
}