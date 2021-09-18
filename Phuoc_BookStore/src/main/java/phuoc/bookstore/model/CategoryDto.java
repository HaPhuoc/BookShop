package phuoc.bookstore.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
	
	private int idCategory;
	
	@NotEmpty
	private String name;
	
	private Boolean isEdit=false;
	
	private int activated;


}
