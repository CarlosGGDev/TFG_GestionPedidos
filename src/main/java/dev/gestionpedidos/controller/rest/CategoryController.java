package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.service.CategoryService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller to manage requests of categories
 */
@RestController
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * POST controller for save a category. When this URL receives a request, the service saves a category
	 * @param response Http response
	 * @param category Object category sended from client
	 * @throws IOException
	 */
	@PostMapping(value = "/categorias/nueva") // http://localhost:8080/categorias/nueva
	public void saveCategory(HttpServletResponse response, @ModelAttribute Category category) throws IOException {
		this.categoryService.saveCategory(category);
		response.sendRedirect("/admin/productos");
	}

	/**
	 * DELETE controller for delete a Category. This controller receives parameters in path.
	 * When this URL receives a request, the service delete a category
	 * @param categoryId Id of category to be removed
	 */
	@DeleteMapping(value = "/categorias/{categoryId}") // http://localhost:8080/categorias/1
	public void deleteCategory(@PathVariable("categoryId") int categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}
}
