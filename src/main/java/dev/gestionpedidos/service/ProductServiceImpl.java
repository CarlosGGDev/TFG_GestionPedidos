package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<List<Product>> getProducts() {
        return Optional.of(this.productRepository.findAll());
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public Optional<Product> saveProduct(Product product) {
        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public Optional<Product> editProduct(Product product) {
        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public void deleteProduct(int productId) {
        Optional<Product> productOpt = getProduct(productId);
        if (productOpt.isPresent()) {
            this.productRepository.deleteById(productId);
        }
    }

}
