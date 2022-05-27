package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
	Optional<List<Product>> getProducts();
	Optional<Product> getProduct(int productId);
	Optional<Product> saveProduct(Product product);
	Optional<Product> editProduct(Product product);
	void deleteProduct(int productId);
}
