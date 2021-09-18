package phuoc.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import phuoc.bookstore.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {



	private int idProduct;
	

	private String name;
	
	private int available;

	private String createDate;

	private String image;

	private float price;

	private int quantity;

	private Category category;
	
	private Boolean isEdit=false;
	
}
