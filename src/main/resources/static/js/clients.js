$(document).ready(function() {

	// REMOVE CLIENT
	$(document).on('click', '#btn-remove', function() {
		if (confirm("¿Seguro que quieres borrar el cliente?\n"
			+ "Se borrará también su historial de pedidos. Esta opción no se puede deshacer.")) {
			let clientId = $(this).closest('tr').children('#client-id').text();
			requestRemoveClient(clientId);
		}
	});

});

const token = $("meta[name='_csrf']").attr("content");

// REQUEST REMOVE CLIENT
function requestRemoveClient(clientId) {
	$.ajax({
		type: "DELETE",
		headers: {
			"X-CSRF-Token": token
		},
		url: "/usuarios/" + clientId
	})
	$('#client-removed').modal('show');
	$('.modal').on('hidden.bs.modal', function () {
		window.location.replace("http://localhost:8080/admin/clientes")
	});
}