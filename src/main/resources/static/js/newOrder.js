$(document).ready(function() {

	// ADD
	$(document).on('click', '#add', function() {

		if ($(this).closest('tr').children('#td-quantity').children('input').val() > 0) {
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
				$("li[id='" + name + "']").children('#item-total').text(productTotal);

				// Call function to update total
				// If is not a new item, the price to add to the total is only the cost of new items (the difference)
				let a = price * quantity
				updateOrderTotal(a);
			} else {
				let li = `<li id="${name}" class="list-group-item d-flex justify-content-between">
							<div class="col-4">
								<h6 class="my-0">${name}</h6>
							</div>
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

	// REMOVE
	$(document).on('click', '#remove', function() {
		// Remove item from bag
		$(this).closest('li').remove();

		// Call function to update total
		let productTotal = parseFloat($(this).closest('li').children('#item-total').html());
		updateOrderTotal(-productTotal);

		// Decrease items cart counter
		updateCounter(-1);
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