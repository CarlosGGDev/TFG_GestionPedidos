package dev.gestionpedidos.controller;

import dev.gestionpedidos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("/productos")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

}