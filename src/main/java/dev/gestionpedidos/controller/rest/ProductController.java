package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/productos/listado") // http://localhost:8080/productos/listado
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.of(productService.getProducts());
    }

    @PostMapping(value = "/productos/nuevo") // http://localhost:8080/productos/nuevo
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.of(productService.saveProduct(product));
    }

    @PostMapping(value = "/productos/editar") // http://localhost:8080/productos/editar
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {
        return ResponseEntity.of(productService.editProduct(product));
    }

    @DeleteMapping(value = "/productos/{productId}") // http://localhost:8080/productos/1
    public void deleteProduct(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
    }
}