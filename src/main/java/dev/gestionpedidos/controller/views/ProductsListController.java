package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML file with products information
 */
@Controller
public class ProductsListController {

    private final CategoryService categoryService;

    public ProductsListController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Controller that returns a products view for users
     * @return HTML file
     */
    @GetMapping(value = "/productos") // http://localhost:8080/productos
    public String showPublicProductsList() {
        return "public/products";
    }

    /**
     * Controller that returns a products view for admin. The products and categories information is sent with a Model object.
     * Send a category Object to be binded by the new category form
     * @param model Object to send data to the view
     * @return HTML file
     */
    @GetMapping(value = "/admin/productos") // http://localhost:8080/admin/productos
    public String showAdminProductsList(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories().get());
        model.addAttribute("category", new Category());
        return "admin/productsAdmin";
    }
}
