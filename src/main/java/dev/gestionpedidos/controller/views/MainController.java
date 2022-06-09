package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.OrderService;
import dev.gestionpedidos.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML files depending on user role
 */
@Controller
public class MainController {

	private final UserService userService;
	private final OrderService orderService;

	public MainController(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}

	/**
	 * Controller that returns a view. This is the controller where the user is redirected when login.
	 * If there is no user saved in session, saves a user with data from database.
	 * If there is a user saved, gets its. This allows to update user profile, go back to main page and update
	 * the user session with new data. If not re-save a user,when this view is opens, the user session has the
	 * old data.
	 *
	 * According to user role, returns a different view
	 * @param userDetails Object with user information managed by Spring Security
	 * @param session Http session
	 * @return ModelAndView object
	 */
	@GetMapping(value = "/") // http://localhost:8080/
	public ModelAndView showMain(@AuthenticationPrincipal UserDetails userDetails, HttpSession session) {
		User user;
		if (session.getAttribute("user") == null) {
			user = this.userService.findByName(userDetails.getUsername()).get();
		} else {
			user = (User) session.getAttribute("user");
		}
		session.setAttribute("user", user);
		ModelAndView main;

		if (user.getRole().name().equals("ROLE_ADMIN")) {
			main = new ModelAndView("admin/mainAdmin");
			main.addObject("pendingOrders", this.orderService.getPendingOrders().get());
			main.addObject("sentOrders", this.orderService.getSentOrders().get());
		} else {
			main = new ModelAndView("public/main");
			main.addObject("pendingOrders", this.orderService.getCustomerPendingOrders(user.getId()).get());
			main.addObject("deliveredOrders", this.orderService.getCustomerDeliveredOrders(user.getId()).get());
		}
		return main;
	}
}