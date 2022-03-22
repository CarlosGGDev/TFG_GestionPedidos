package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // CONSTRUCTORS
    public ProductService() {
    }

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
    // METHODS
    public boolean newProduct(String name, Category category, double price) {
        // TODO:
    }
    */

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /*
    public Product getProduct(int id) {
        // TODO:
    }

    public boolean deleteProduct(int id) {
        // TODO:
    }

    public boolean editProduct(int id) {
        // TODO:
    }
     */
}
