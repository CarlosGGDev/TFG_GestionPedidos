$(document).ready(function() {

	$('#form').submit(function(event) {
		let password = $('#password').val();
		let confirmPassword = $('#confirm-password').val();

		if (password != confirmPassword) {
			event.preventDefault();
		} else {
			// This includes check if the users is in sign up page.
			// If so, then we can show confirmation message.
			// All this process allows us to re-use the same javscript file for two pages
			if (window.location.toString().includes('perfil')) {
				if (confirm('¿Quieres actualizar tus datos?')) {
					$(this).unbind('submit').submit();
				}
			}
		}
	});

	$('#password, #confirm-password').on('keyup', function () {
		let password = $('#password').val();
		let confirmPassword = $('#confirm-password').val();

		if (password == confirmPassword && password != "" && confirmPassword != "") {
			$('#icon-check').attr('hidden', false);
			$('#icon-alert').attr('hidden', true);
		} else {
			$('#icon-check').attr('hidden', true);
			$('#icon-alert').attr('hidden', false);
		}
	});

});