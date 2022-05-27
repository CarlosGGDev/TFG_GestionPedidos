$(document).ready(function() {

	// ADD ITEM CART
	$(document).on('click', '#add', function() {

		if ($(this).closest('tr').children('td:nth-child(4)').children('input').val() > 0) {
		    if ($('#empty-bag-message')) {
		        $('#empty-bag-message').remove();
		    }

			let id = $(this).closest('tr').children('td:nth-child(1)').text();
			let name = $(this).closest('tr').children('td:nth-child(2)').text();
			let price = $(this).closest('tr').children('td:nth-child(3)').text();
			let quantity = $(this).closest('tr').children('td:nth-child(4)').children('input').val();
			let productTotal = (price * quantity).toFixed(2);

            // UPDATE CART ITEMS
            // If exist an element with id="id", updates data, if not, creates a new element <li>
			if (document.getElementById(`${id}`)) {
				updatesItemQuantityTotal(id, quantity, price);
			} else {
				let li = `<li id="${id}" class="list-group-item d-flex justify-content-between">
							<span class="col-4" hidden>${id}</span>
							<span class="col-4" hidden>${price}</span>
							<span class="col-4">${name}</span>
							<span class="col-3 text-center">${quantity}</span>
							<span class="col-3">${productTotal} €</span>
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
		let productTotal = parseFloat($(this).closest('li').children('span:nth-child(5)').html());
		updateOrderTotal(-productTotal);

		// Decrease items cart counter
		updateCounter(-1);

		if ($('#items-counter').text() == 0) {
			addEmptyBagMessage();
		}
	});

	// EMPTY CART
	$(document).on('click', '#empty-cart', function() {
		// Remove all items from cart
		$('#cart-body').empty();
		addEmptyBagMessage();

        // Update order total
		let total = 0;
		total = total.toFixed(2);
		$('#order-total').html(total);

		// Update cart counter
		$('#items-counter').html(0);
	});

	// CONFIRM ORDER
	$(document).on('click', '#confirm-order', function() {
		if ($('#items-counter').html() > 0) {
			if (confirm('¿Deseas confirmar el pedido?')) {
			    // GENERATE ORDER
                let orderTotal = getOrderTotal();
				let order = generateOrder(orderTotal);
                requestOrder(order);
			}
		}
	});
});

const token = $("meta[name='_csrf']").attr("content");

// ADD EMPTY BAG MESSAGE
function addEmptyBagMessage() {
	let message = "<li id='empty-bag-message' class='bg-white p-3 text-muted'>Tu cesta está vacía</li>"
    $('#cart-body').append(message);
}

// UPDATE ITEMS BAG COUNTER
function updateCounter(num) {
	let counter = parseFloat($('#items-counter').html()) + num;
	$('#items-counter').text(counter);
}

// UPDATES CART ITEM QUANTITY & TOTAL
function updatesItemQuantityTotal(itemId, quantity, price) {
    // Updates item quantity
    let itemQuantity = $("li[id='" + itemId + "']").children('span:nth-child(4)').text();
    let newQuantity = parseInt(itemQuantity) + parseInt(quantity);
    $("li[id='" + itemId + "']").children('span:nth-child(4)').text(newQuantity);

    // Updates product total
    productTotal = (newQuantity * price).toFixed(2);
    $("li[id='" + itemId + "']").children('span:nth-child(5)').text(productTotal + ' €');

    // Updates order total
	// If is not a new item, the price to add to the total is only the cost
	// of new items (the difference), not the total product cost
    let newItemPrice = price * quantity
    updateOrderTotal(newItemPrice);
}

// UPDATE ORDER TOTAL €
function updateOrderTotal(productTotal) {
	let total = parseFloat($('#order-total').html());
	total = total + productTotal;
	total = total.toFixed(2);
	$('#order-total').text(total);
}

// CALCULATE ORDER TOTAL
function getOrderTotal() {
    let total = 0;
    let orderTotal = 0;
    $('#cart-body').children().each(function() {
        total = $(this).children('span:nth-child(5)').html();
        orderTotal = (orderTotal + parseFloat(total));
    });
    orderTotal = orderTotal.toFixed(2);
    return orderTotal;
}

// CREATE ORDER
function generateOrder(orderTotal) {
    /* The user field is added on server side */
    var date = new Date();
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
    return order;
}

// CREATES AN ORDER DETAIL
function generateOrderDetail(row) {
    let id = $(row).children('span:nth-child(1)').html();
    let quantity = $(row).children('span:nth-child(4)').html();
    let price = $(row).children('span:nth-child(2)').html();
    let total = $(row).children('span:nth-child(5)').html();

    let orderDetail = {
    	"product_id": id,
    	"quantity": quantity,
    	"price": price,
    	"total": parseFloat(total)
    }
    return orderDetail;
}

// REQUEST ORDER
function requestOrder(order) {
	$.post({
		type: "POST",
		headers: {
			"X-CSRF-Token": token
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		url: "/pedidos",
		data: JSON.stringify(order),
		success: function() {

			// GENERATE ORDER DETAILS
			$('#cart-body').children().each(function() {
			    // This call returns an order detail
				let orderDetail = generateOrderDetail(this);
				// REQUEST METHOD CALL
				requestOrderDetail(orderDetail);
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
function requestOrderDetail(orderDetail) {
	$.post({
		type: "POST",
		headers: {
			"X-CSRF-Token": token
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		url: "/pedidos/detalle",
		data: JSON.stringify(orderDetail)
	})
}