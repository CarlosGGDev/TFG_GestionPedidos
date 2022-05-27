package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsListController {

    private final CategoryService categoryService;

    public ProductsListController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/productos") // http://localhost:8080/productos
    public String showPublicProductsList(Model model) {
        return "public/products";
    }

    @GetMapping(value = "/admin/productos") // http://localhost:8080/admin/productos
    public String showAdminProductsList(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories().get());
        model.addAttribute("category", new Category());
        return "admin/productsAdmin";
    }
}
