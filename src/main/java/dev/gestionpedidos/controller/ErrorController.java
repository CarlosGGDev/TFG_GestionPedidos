package dev.gestionpedidos.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			int statusCode = Integer.parseInt(status.toString());
			String message = "";

			if (statusCode == HttpStatus.BAD_REQUEST.value()) {
				message = "Petición incorrecta";
			} else if (statusCode == HttpStatus.NON_AUTHORITATIVE_INFORMATION.value()) {
				message = "No está autorizado";
			} else if (statusCode == HttpStatus.NOT_FOUND.value()) {
				message = "Página no encontrada";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				message = "Error del servidor";
			}
			model.addAttribute("errorMessage", message);
		}
		return "error";
	}

}
