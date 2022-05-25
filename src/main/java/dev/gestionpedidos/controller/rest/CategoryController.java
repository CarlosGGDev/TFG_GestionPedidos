package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.service.CategoryService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping(value = "/categorias/nueva") // http://localhost:8080/categorias/nueva
	public void saveCategory(HttpServletResponse response, @ModelAttribute Category category) throws IOException {
		Optional<Category> categoryOpt = this.categoryService.saveCategory(category);
		response.sendRedirect("/admin/productos");
	}

	@DeleteMapping(value = "/categorias/{categoryId}") // http://localhost:8080/categorias/1
	public ResponseEntity<Category> deleteCategory(@PathVariable("categoryId") int categoryId) {
		Optional<Category> categoryOpt = this.categoryService.deleteCategory(categoryId);
		return ResponseEntity.of(categoryOpt);
	}
}
