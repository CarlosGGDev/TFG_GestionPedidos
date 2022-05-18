package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping(value = "/nueva") // http://localhost:8080/categorias/nueva
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		return ResponseEntity.ok(this.categoryService.saveCategory(category));
	}
}
