package phuoc.bookstore.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	private int idProduct;
	
	@NotBlank(message = "Khong duoc de trong")
	private String name;
	
	private int available;

	private String createDate;
	
	private String descreption;

	private int saled;
	
	public int getSaled() {
		return saled;
	}

	public void setSaled(int saled) {
		this.saled = saled;
	}

	private String image;
	@NotNull
	@DecimalMin("1")
	private float price;
	@NotNull
	@DecimalMin("1")
	private int quantity;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="product")
	private List<OrderDetail> orderDetails;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="IdCategory")
	private Category category;

	public Product() {
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getAvailable() {
		return this.available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDescreption() {
		return this.descreption;
	}

	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setProduct(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setProduct(null);

		return orderDetail;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}