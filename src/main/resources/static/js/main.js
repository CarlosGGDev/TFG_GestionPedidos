$(document).ready(function() {

	// REMOVE ORDER (only with status 'pendiente' or 'enviado')
	$(document).on('click', '#btn-remove', function() {
		if (confirm("¿Seguro que quieres borrar el pedido?\nEsta opción no se puede deshacer")) {
			let orderId = $(this).closest('tr').children('#order-id').text();
			requestRemoveOrder(orderId);
		}
	});

});

const token = $("meta[name='_csrf']").attr("content");

// REQUEST REMOVE ORDER
function requestRemoveOrder(orderId) {
	$.ajax({
		type: "DELETE",
		headers: {
			"X-CSRF-Token": token
		},
		url: "/pedidos/" + orderId
	})
	$('#order-removed').modal('show');
	$('.modal').on('hidden.bs.modal', function () {
		window.location.replace("http://localhost:8080")
	});
}