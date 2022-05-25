package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    // TODO: anotaciones @Valid para validar campos

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/productos/listado") // http://localhost:8080/productos/listado
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping(value = "/productos/nuevo") // http://localhost:8080/productos/nuevo
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PostMapping(value = "/productos/editar") // http://localhost:8080/productos/editar
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.editProduct(product));
    }

    @DeleteMapping(value = "/productos/{productId}") // http://localhost:8080/productos/1
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") int productId) {
        Optional<Product> productOpt = productService.deleteProduct(productId);
        return ResponseEntity.of(productOpt);
    }
}