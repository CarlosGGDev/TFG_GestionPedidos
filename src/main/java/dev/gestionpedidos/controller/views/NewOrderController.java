package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewOrderController {

	private final ProductService productService;

	public NewOrderController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = "/nuevo_pedido") // http://localhost:8080/nuevo/pedido
	public String showNewOrder(Model model) {
		model.addAttribute("products", this.productService.getProducts());
		return "public/newOrder";
	}

}
