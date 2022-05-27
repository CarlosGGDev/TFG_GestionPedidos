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

@Controller
public class ProfileController {

	private final UserService userService;

	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/perfil") // http://localhost:8080/perfil
	public String showPublicProfile() {
		return "public/profile";
	}

	@GetMapping(value = "/admin/perfil") // http://localhost:8080/admin/perfil
	public String showAdminProfile() {
		return "admin/profileAdmin";
	}

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

			// Es necesario volver a guardar el usuario en la sesion, ya que ahora el usuario ha cambiado,
			// si no al ir al menu no encuentra los datos del usuario "original" y da error
			session.setAttribute("user", user);
		}

		return sessionUser.getRole().toString().equals("ROLE_USER") ? "public/profile" : "admin/profile";
	}

}
