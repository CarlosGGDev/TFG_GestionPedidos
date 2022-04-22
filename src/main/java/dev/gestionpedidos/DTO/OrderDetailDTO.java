package dev.gestionpedidos.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

	private int order_id;
	private int product_id;
	private int quantity;
	private double price;
	private double total;

}
