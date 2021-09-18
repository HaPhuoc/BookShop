package phuoc.bookstore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the acounts database table.
 * 
 */
@Entity
@Table(name="acounts")
@NamedQuery(name="Acount.findAll", query="SELECT a FROM Acount a")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Acount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank(message = "Username phải được nhập !")
	@Column(unique = true)
	private String username;
	private int activated;
	private int admin;
	@NotBlank(message = "*Không được để trống email !")
	private String email;
	@NotBlank(message = "Không để trống tên!")
	private String fullname;
	@NotBlank(message = "Không được để trống mật khẩu !")
	private String password;
	
	@NotBlank(message = "Không Để trống địa chỉ!")
	private String address;
	
	private String createDate;
	
	private String photo;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="acount")
	private List<Order> orders;


	public @NotBlank(message = "Username phải được nhập !") String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	

}