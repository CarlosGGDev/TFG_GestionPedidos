$(document).ready(function() {

	// ADD ITEM CART
	$(document).on('click', '#add', function() {

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
							<button id="remove" class="btn btn-sm btn-remove col-1"><i class="bi bi-trash3"></i></button>
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
	});

	// EMPTY CART
	$(document).on('click', '#empty-cart', function() {
		// Remove all items from cart
		$('#cart-body').empty();

		// Update order total
		$('#order-total').html(0);

		// Update cart counter
		$('#items-counter').html(0);
	});

	// CONFIRM ORDER
	$(document).on('click', '#confirm-order', function() {
		let orderTotal = 0;

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

			// GENERATE ORDER
			let order = {
				/* ****** PONER EL USER DESDE EL CONTROLADOR DE SPRING ****** */
				"user": {
					"id": 1,
					"nif": "43218637G",
					"name": "Carlos",
					"email": "carlos@gmail.com",
					"phone": "636123456",
					"adress": "C/ Anonima, 23",
					"zipcode": 7003,
					"town": "Palma",
					"password": "$2a$10$hwiDfYCKG0Fhnp90S4KAi.ExI0mdRnln5p20X0T34Pzug7dWhWPre",
					"role": "ROLE_USER"
				},
				"orderDate": new Date(),
				"shippingDate": null,
				"status": "pendiente",
				"comment": "Comentario de prueba",
				"total": orderTotal
			}
			requestOrder(order);

			// GENERATE ORDER DETAILS

		}
	});

});

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
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		url: "/pedidos",
		data: JSON.stringify(data),
		success: function() {
			window.location.href="orderSuccess.html";
		},
		error: function() {
			alert("El pedido no se ha podido realizar");
		}
	})
}

// REQUEST ORDER DETAIL