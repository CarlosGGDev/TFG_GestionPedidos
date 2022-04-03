package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
	List<Product> getProducts();
	Optional<Product> getProduct(int productId);
	Optional<List<Product>> getProductsByCategory(int categoryId);
	Product saveProduct(Product product);
	Optional<Product> deleteProduct(int productId);
	// TODO: metodo editProduct()
	boolean editProduct(int productId);
}
