package dev.gestionpedidos.dto;

import lombok.*;

/**
 * Class with DTO object to be saved in the database. By using a plain object
 * we can persist the object  without needing to know its relationships with
 * another entities.
 */
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

	private int order_id;
	private int product_id;
	private int quantity;
	private double price;
	private double total;

}
