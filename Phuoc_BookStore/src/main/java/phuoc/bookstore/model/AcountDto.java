package phuoc.bookstore.model;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcountDto {
	@Id

	private String username;
	
	private int activated;
	
	private int admin;

	private String email;

	private String fullname;

	private String password;
	
	private String createDate;
	
	private String photo;
	
	private String address;
	
	private Boolean isEdit=false;
	
}
