package dev.gestionpedidos.controller.views;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return a custom HTML file with HTTP errors
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	/**
	 * Controller that returns a view. The error information is sent with a Model object.
	 * According to the HTTP error, the error message changes.
	 * The error is obtained from RequestDispatcher, which communicates with servlet container.
	 * @param model Object to send data to the view
	 * @return HTML file
	 */
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
		return "public/error";
	}
}