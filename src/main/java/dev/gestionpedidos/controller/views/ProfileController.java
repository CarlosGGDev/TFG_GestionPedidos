package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML file with profile information, and allows user to update it
 */
@Controller
public class ProfileController {

	private final UserService userService;

	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Controller that returns a view for users with profile information
	 * @return HTML file
	 */
	@GetMapping(value = "/perfil") // http://localhost:8080/perfil
	public String showPublicProfile() {
		return "public/profile";
	}

	/**
	 * Controller that returns a view for admin with profile information
	 * @return HTML file
	 */
	@GetMapping(value = "/admin/perfil") // http://localhost:8080/admin/perfil
	public String showAdminProfile() {
		return "admin/profileAdmin";
	}

	/**
	 * POST controller that updates a user profile.
	 * Gets the submitted user and sets the id and role of the session user.
	 * To check that the new user information doesnt'exist in database, it does
	 * a search for each value. It allow us to show a specific message for each
	 * field, and know what value exists.
	 *
	 * Saves the user in session, because the user now has a different data that
	 * when he logged in
	 *
	 * @param user User with new data to be saved
	 * @param session Http session
	 * @param model Object to bind data between controller and view
	 * @return HTML file according a user role
	 */
	@PostMapping(value = "/perfil") // http://localhost:8080/registro
	public String updateProfile(@Valid @ModelAttribute User user,
								HttpSession session,
								Model model) {
		User sessionUser = (User) session.getAttribute("user");
		user.setId(sessionUser.getId());
		user.setRole(sessionUser.getRole());

		Optional<User> nameEntry = this.userService.findByName(user.getName());
		Optional<User> emailEntry = this.userService.findByEmail(user.getEmail());
		Optional<User> nifEntry = this.userService.findByNif(user.getNif());
		boolean valid = true;

		if (nameEntry.isPresent() && nameEntry.get().getId() != user.getId()) {
			model.addAttribute("nameError", "El nombre ya existe");
			valid = false;
		}
		if (emailEntry.isPresent() && emailEntry.get().getId() != user.getId()) {
			model.addAttribute("emailError", "El email ya existe");
			valid = false;
		}
		if (nifEntry.isPresent() && nifEntry.get().getId() != user.getId()) {
			model.addAttribute("nifError", "El NIF ya existe");
			valid = false;
		}
		if (valid) {
			this.userService.saveUser(user);
			session.setAttribute("user", user);
		}
		return sessionUser.getRole().toString().equals("ROLE_USER") ? "public/profile" : "admin/profile";
	}

}
