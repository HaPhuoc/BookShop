package phuoc.bookstore.model;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	
	@Id
	private int idOrder;

	private String address;

	private String createDate;
	
	private String username;
	
	private int status;


}
