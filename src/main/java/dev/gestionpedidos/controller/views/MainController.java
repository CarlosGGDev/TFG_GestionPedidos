package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.OrderService;
import dev.gestionpedidos.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class MainController {

	private final UserService userService;
	private final OrderService orderService;

	public MainController(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}

	@GetMapping // http://localhost:8080/
	public ModelAndView showMain(@AuthenticationPrincipal UserDetails userDetails, HttpSession session) {
		// Si no hay un usuario guardado en la sesion, lo guarda (solo cuando se inicia sesion por primera vez).
		// Si hay un usuario guardado, no lo vuelve a guardar, de esta forma cuando se actualizan los datos del perfil y se guarda
		// el nuevo usuario en la sesion, puede recuperar los datos del usuario, si no da error, ya que intenta recuperar los datos del usuario
		// que habia iniciado sesion, pero al cambiar los datos del perfil da error.
		User user;
		if (session.getAttribute("user") == null) {
			user = userService.findByName(userDetails.getUsername()).get();
		} else {
			user = (User) session.getAttribute("user");
		}
		session.setAttribute("user", user);
		ModelAndView main;

		if (user.getRole().name().equals("ROLE_ADMIN")) {
			main = new ModelAndView("admin/main");
			main.addObject("pendingOrders", orderService.getPendingOrders().get());
			main.addObject("sentOrders", orderService.getSentOrders().get());
		} else {
			main = new ModelAndView("public/main");
			main.addObject("pendingOrders", orderService.getCustomerPendingOrders(user.getId()).get());
			main.addObject("deliveredOrders", orderService.getCustomerDeliveredOrders(user.getId()).get());
		}
		return main;
	}

}
