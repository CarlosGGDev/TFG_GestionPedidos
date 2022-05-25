$(document).ready(function() {

	// ADD ITEM CART
	$(document).on('click', '#add', function() {

		$('#empty').remove();

		if ($(this).closest('tr').children('#td-quantity').children('input').val() > 0) {
			let id = $(this).closest('tr').children('#td-id').text();
			let name = $(this).closest('tr').children('#td-name').text();
			let price = $(this).closest('tr').children('#td-price').text();
			let quantity = $(this).closest('tr').children('#td-quantity').children('input').val();
			let productTotal = price * quantity;
			productTotal = productTotal.toFixed(2);

			if (document.getElementById(`${name}`)) {
				// INCREMENTS ITEM QUANTITY
				let itemQuantity = $("li[id='" + name + "']").children('#item-quantity').text();
				let newQuantity = parseInt(itemQuantity) + parseInt(quantity);
				$("li[id='" + name + "']").children('#item-quantity').text(newQuantity);

				// INCREMENTS ITEM TOTAL
				productTotal = newQuantity * price;
				productTotal = productTotal.toFixed(2);
				$("li[id='" + name + "']").children('#item-total').text(productTotal + ' €');

				// Call function to update total
				// If is not a new item, the price to add to the total is only the cost of new items (the difference)
				let a = price * quantity
				updateOrderTotal(a);
			} else {
				let li = `<li id="${name}" class="list-group-item d-flex justify-content-between">
							<span id="item-id" class="col-4" hidden>${id}</span>
							<span id="item-price" class="col-4" hidden>${price}</span>
							<span id="item-name" class="col-4">${name}</span>
							<span id="item-quantity" class="col-3 text-center">${quantity}</span>
							<span id="item-total" class="col-3">${productTotal} €</span>
							<button id="remove" class="btn btn-sm btn-custom col-1"><i class="bi bi-trash3"></i></button>
						</li>`;

				// Add element <li>
				$('#cart-body').append(li);

				// Call function to update total
				// If is a new item, the price to add to the total is the new items cost
				updateOrderTotal(parseFloat(productTotal));

				// Increment items cart counter
				updateCounter(+1);
			}
		}
	});

	// REMOVE ITEM CART
	$(document).on('click', '#remove', function() {
		// Remove item from cart
		$(this).closest('li').remove();

		// Call function to update total
		let productTotal = parseFloat($(this).closest('li').children('#item-total').html());
		updateOrderTotal(-productTotal);

		// Decrease items cart counter
		updateCounter(-1);

		if ($('#items-counter').text() == 0) {
			let message = "<li id='empty' class='bg-white p-3 text-muted'>Tu cesta está vacía</li>"
			$('#cart-body').append(message);
		}
	});

	// EMPTY CART
	$(document).on('click', '#empty-cart', function() {
		// Remove all items from cart
		$('#cart-body').empty();
		let message = "<li id='empty' class='bg-white p-3 text-muted'>Tu cesta está vacía</li>"
		$('#cart-body').append(message);

		// Update order total
		$('#order-total').html(0);

		// Update cart counter
		$('#items-counter').html(0);
	});

	// CONFIRM ORDER
	$(document).on('click', '#confirm-order', function() {
		let orderTotal = 0;

		if ($('#items-counter').html() > 0) {
			if (confirm('¿Deseas confirmar el pedido?')) {
				$('#cart-body').children().each(function() {
					let id = $(this).children('#item-id').html();
					let name = $(this).children('#item-name').html();
					let quantity = $(this).children('#item-quantity').html();
					let price = $(this).children('#item-price').html();
					let total = $(this).children('#item-total').html();
					orderTotal += parseFloat(total);
				});
				orderTotal = orderTotal.toFixed(2);

				if (orderTotal > 0) {
					// GENERATE ORDER
					/* The user field is added by the controller */
					let date = new Date();
					var shippingAddress;
					if ($('#new-address').is(':checked')) {
						shippingAddress = $('input[name="new-address"]').val();
					}
					date.setHours(date.getHours()+2);
					let order = {
						"orderDate": date,
						"shippingAddress": shippingAddress,
						"status": "pendiente",
						"comment": null,
						"total": orderTotal
					}
					requestOrder(order);
				}
			}
		}
	});
});

const token = $("meta[name='_csrf']").attr("content");

// UPDATE ITEMS BAG COUNTER
function updateCounter(num) {
	let counter = parseFloat($('#items-counter').html()) + num;
	$('#items-counter').text(counter);
}

// UPDATE ORDER TOTAL €
function updateOrderTotal(productTotal) {
	let total = parseFloat($('#order-total').html());
	total = total + productTotal;
	total = total.toFixed(2);
	$('#order-total').text(total);
}

// REQUEST ORDER
function requestOrder(data) {
	$.post({
		type: "POST",
		headers: {
			"X-CSRF-Token": token
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		url: "/pedidos",
		data: JSON.stringify(data),
		success: function() {

			// GENERATE ORDER DETAILS
			$('#cart-body').children().each(function() {
				let id = $(this).children('#item-id').html();
				let quantity = $(this).children('#item-quantity').html();
				let price = $(this).children('#item-price').html();
				let total = $(this).children('#item-total').html();

				let detail = {
					"product_id": id,
					"quantity": quantity,
					"price": price,
					"total": parseFloat(total)
				}

				// REQUEST METHOD CALL
				requestOrderDetail(detail);
			});

			$('#order-success').modal('show');
			$('.modal').on('hidden.bs.modal', function () {
				window.location.replace("http://localhost:8080")
			});
		},
		error: function() {
			alert("El pedido no se ha podido realizar");
		}
	})
}

// REQUEST ORDER DETAIL
function requestOrderDetail(data) {
	$.post({
		type: "POST",
		headers: {
			"X-CSRF-Token": token
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		url: "/pedidos/detalle",
		data: JSON.stringify(data)
	})
}