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
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "admin/profile";
	}

	@PostMapping // http://localhost:8080/registro
	public String updateProfile(@Valid @ModelAttribute User user, HttpSession session, Model model) {
		User sessionUser = (User) session.getAttribute("user");
		user.setId(sessionUser.getId());

		User nameEntry = userService.findByName(user.getName());
		User emailEntry = userService.findByEmail(user.getEmail());
		User nifEntry = userService.findByNif(user.getNif());
		Boolean valid = true;

		if (nameEntry != null && nameEntry.getId() != user.getId()) {
			model.addAttribute("nameError", "El nombre ya existe");
			valid = false;
		}
		if (emailEntry != null && emailEntry.getId() != user.getId()) {
			model.addAttribute("emailError", "El email ya existe");
			valid = false;
		}
		if (nifEntry != null && nifEntry.getId() != user.getId()) {
			model.addAttribute("nifError", "El NIF ya existe");
			valid = false;
		}
		if (valid){
			// El metodo save actualiza el usuario
			userService.saveUser(user);

			// Es necesario volver a guardar el usuario en la sesion, ya que ahora el usuario ha cambiado,
			// si no al ir al menu no encuentra los datos del usuario "original" y da error
			session.setAttribute("user", user);
		}

		/*if (user.getRole().toString() == "ROLE_ADMIN") {
			return "admin/profile";
		}
		return "public/profile";*/
		// TOREV: comprobar si devuelve bien la vista de admin o user al actualizar el perfil
		return sessionUser.getRole().toString().equals("ROLE_USER") ? "public/profile" : "admin/profile";
	}

}
