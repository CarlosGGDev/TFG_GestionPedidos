package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML file to make a new order
 */
@Controller
public class NewOrderController {

	private final ProductService productService;

	public NewOrderController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Controller that returns a view. The products information is sent with a Model object
	 * @param model Object to send data to the view
	 * @return HTML file
	 */
	@GetMapping(value = "/nuevo_pedido") // http://localhost:8080/nuevo_pedido
	public String showNewOrder(Model model) {
		model.addAttribute("products", this.productService.getProducts().get());
		return "public/newOrder";
	}

}
