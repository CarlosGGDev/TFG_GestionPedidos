package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/productos")
public class ProductController {

    // TODO: anotaciones @Valid para validar campos

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping // http://localhost:8080/productos
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping(value = "/{productId}") // http://localhost:8080/productos/1
    public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {
        Optional<Product> productOpt = productService.getProduct(productId);
        return ResponseEntity.of(productOpt);
    }

    @GetMapping(value = "/categoria/{categoryId}") // http://localhost:8080/productos/categoria/1
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("categoryId") int categoryId) {
        Optional<List<Product>> productsOpt = productService.getProductsByCategory(categoryId);
        return ResponseEntity.of(productsOpt);
    }

    @PostMapping // http://localhost:8080/productos
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productOpt = productService.saveProduct(product);
        return ResponseEntity.ok(productOpt);
    }

    @DeleteMapping(value = "/{productId}") // http://localhost:8080/productos/1
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") int productId) {
        Optional<Product> productOpt = productService.deleteProduct(productId);
        return ResponseEntity.of(productOpt);
    }

}