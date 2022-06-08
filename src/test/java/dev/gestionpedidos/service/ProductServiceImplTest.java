package dev.gestionpedidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.gestionpedidos.model.Category;
import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * ProductService class test cases
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	public static final int ONE = 1;
	public static final String NAME = "name";
	public static final double PRICE = 1.5;

	@Mock
	private ProductRepository productRepository;
	@InjectMocks
	private ProductServiceImpl productService;

	Category category = Category.builder()
		.id(ONE)
		.name(NAME)
		.build();

	Product product = Product.builder()
		.id(ONE)
		.name(NAME)
		.category(this.category)
		.price(PRICE)
		.build();

	List<Product> products = List.of(product);

	/**
	 * Check that the service returns all products
	 */
	@Test
	void should_return_all_products() {
		when(this.productRepository.findAll()).thenReturn(this.products);

		Optional<List<Product>> products = this.productService.getProducts();

		assertEquals(products.get(), this.products);
	}

	/**
	 * Check that the service returns a product
	 */
	@Test
	void should_return_one_product() {
		when(this.productRepository.findById(ONE)).thenReturn(Optional.of(this.product));

		Optional<Product> product = this.productService.getProduct(ONE);

		assertEquals(product.get(), this.product);
	}

	/**
	 * Check that the service saves a product
	 */
	@Test
	void should_save_one_product() {
		when(this.productRepository.save(this.product)).thenReturn(this.product);

		Optional<Product> product = this.productService.saveProduct(this.product);

		assertEquals(product.get(), this.product);
	}

	/**
	 * Check that the service edits a product
	 */
	@Test
	void should_edit_one_product() {
		when(this.productRepository.save(this.product)).thenReturn(this.product);

		Optional<Product> product = this.productService.editProduct(this.product);

		assertEquals(product.get(), this.product);
	}

	/**
	 * Check that the service deletes a product
	 */
	@Test
	void should_delete_one_product() {
		when(this.productService.getProduct(ONE)).thenReturn(Optional.of(this.product));
		doNothing().when(this.productRepository).deleteById(ONE);

		this.productService.deleteProduct(ONE);

		verify(this.productRepository, times(1)).deleteById(ONE);
	}

}