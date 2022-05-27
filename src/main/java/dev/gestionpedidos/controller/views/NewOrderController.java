package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewOrderController {

	private final ProductService productService;

	public NewOrderController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = "/nuevo_pedido") // http://localhost:8080/nuevo_pedido
	public String showNewOrder(Model model) {
		model.addAttribute("products", this.productService.getProducts().get());
		return "public/newOrder";
	}

}
