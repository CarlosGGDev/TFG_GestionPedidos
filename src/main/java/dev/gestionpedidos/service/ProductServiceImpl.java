package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // CONSTRUCTORS

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CRUD METHODS

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.findById(productId);
    }

    public Optional<List<Product>> getProductsByCategory(int categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }

    public Product saveProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Optional<Product> deleteProduct(int productId) {
        Optional<Product> productOpt = getProduct(productId);
        if (productOpt.isPresent()) {
            productRepository.deleteById(productId);
        }
        return productOpt;
    }

    public boolean editProduct(int productId) {
        // TODO
        // editar tambien dato return firma metodo (boolean)
        return true;
    }

}
