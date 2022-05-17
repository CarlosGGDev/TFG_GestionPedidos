package dev.gestionpedidos.controller;

import dev.gestionpedidos.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/productos")
public class ProductsListController {

    private final CategoryService categoryService;

    public ProductsListController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping // http://localhost:8080/productos
    public String showProductsList(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
        return "products";
    }

}
