package phuoc.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

	private String image;
	private Integer idProduct;
	private String name;
	private float price;
	private int quantity;
	private String username;
	private boolean status=false;
}
