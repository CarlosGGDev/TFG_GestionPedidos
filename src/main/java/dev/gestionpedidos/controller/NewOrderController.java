package dev.gestionpedidos.controller;

import dev.gestionpedidos.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/nuevo_pedido")
public class NewOrderController {

	private final ProductService productService;

	public NewOrderController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public String showNewOrder(Model model) {
		model.addAttribute("products", this.productService.getProducts());
		return "newOrder";
	}

}
