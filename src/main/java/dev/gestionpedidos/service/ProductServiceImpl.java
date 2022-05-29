package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Product service implementation.
 * The Product repository is injected as dependency at controller level.
 * Methods invoke the repository to persist data.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products
     * @return Optional of products list
     */
    @Override
    public Optional<List<Product>> getProducts() {
        return Optional.of(this.productRepository.findAll());
    }

    /**
     * Get a product by id
     * @param productId Product id
     * @return Optional of product
     */
    @Override
    public Optional<Product> getProduct(int productId) {
        return this.productRepository.findById(productId);
    }

    /**
     * Saves a product
     * @param product Product to be saved
     * @return Optional of product
     */
    @Override
    public Optional<Product> saveProduct(Product product) {
        return Optional.of(this.productRepository.save(product));
    }

    /**
     * Edits a product. The repository replaces the product with the same id.
     * @param product Product to be saved
     * @return Optional of product
     */
    @Override
    public Optional<Product> editProduct(Product product) {
        return Optional.of(this.productRepository.save(product));
    }

    /**
     * Deletes a product
     * @param productId Id of the product to be deleted
     */
    @Override
    public void deleteProduct(int productId) {
        Optional<Product> productOpt = getProduct(productId);
        if (productOpt.isPresent()) {
            this.productRepository.deleteById(productId);
        }
    }

}
