$(document).ready(function() {

	$(document).on('click', '#add', function() {

		if ($(this).closest('tr').children('#product-quantity').children('input').val() > 0) {
			let name = $(this).closest('tr').children('#product-name').text();
			let price = $(this).closest('tr').children('#product-price').text();
			let quantity = $(this).closest('tr').children('#product-quantity').children('input').val();
			let total = price * quantity;
			total = total.toFixed(2);
			let li = `<li class="list-group-item d-flex justify-content-between">
							<div class="col-4">
								<h6 class="my-0">${name}</h6>
							</div>
							<span class="col-3 text-center">${quantity}</span>
							<span id="product-total" class="col-2">${total} €</span>
							<button id="remove" class="btn btn-sm btn-remove col-1"><i class="bi bi-trash3"></i></button>
						</li>`;
			$('#cart-footer').before(li);

			// Increment items cart counter
			let counter = parseFloat($('#products-counter').html()) + 1;
			$('#products-counter').text(counter);

			// Add new item price to the total order
			let orderTotal = parseFloat($('#total').html());
			orderTotal += parseFloat(total);
			orderTotal = orderTotal.toFixed(2);
			$('#total').text(orderTotal);
		}
	});

	$(document).on('click', '#remove', function() {
		$(this).closest('li').remove();

		let price = $(this).closest('li').children('#product-total').html();
		console.log(price);

		// Remove new item price to the total order
		let productTotal = parseFloat($(this).closest('li').children('#product-total').html());
		let total = $('#total').text() - productTotal;
		total = total.toFixed(2);
		$('#total').text(total);

		// Decrease items cart counter
		let counter = parseFloat($('#products-counter').html()) - 1;
		$('#products-counter').text(counter);
	});

});