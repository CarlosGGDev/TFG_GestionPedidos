package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller to manage requests of products
 */
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * POST controller for get all prodcts. When this URL receives a request, the service return a list of products.
     * @return Response an optional object with the list of products
     */
    @PostMapping(value = "/productos/listado") // http://localhost:8080/productos/listado
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.of(this.productService.getProducts());
    }

    /**
     * POST controller for save a product. When this URL receives a request, the service saves a product.
     * @param product The product to be saved
     * @return Response an optional of product saved
     */
    @PostMapping(value = "/productos/nuevo") // http://localhost:8080/productos/nuevo
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.of(this.productService.saveProduct(product));
    }

    /**
     * POST controller for edit a product. When this URL receives a request, the service edits a product.
     * The product is not updated, the repository find a product with the same id, and replaces it.
     * @param product New product to be saved
     * @return
     */
    @PostMapping(value = "/productos/editar") // http://localhost:8080/productos/editar
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {
        return ResponseEntity.of(this.productService.editProduct(product));
    }

    /**
     * POST controller for delete a product
     * @param productId Id of the product to be saved
     */
    @DeleteMapping(value = "/productos/{productId}") // http://localhost:8080/productos/1
    public void deleteProduct(@PathVariable("productId") int productId) {
        this.productService.deleteProduct(productId);
    }
}