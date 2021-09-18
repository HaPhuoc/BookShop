package phuoc.bookstore.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the order_details database table.
 * 
 */
@Entity
@Table(name="order_details")
@NamedQuery(name="OrderDetail.findAll", query="SELECT o FROM OrderDetail o")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrderDetail;


	public void setIdOrder(Order idOrder) {
		this.idOrder = idOrder;
	}

	private double price;

	private int quantity;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="IdProduct")
	private Product product;
	@ManyToOne
	@JoinColumn(name="idOrder")
	private Order idOrder;
	
	public Order getIdOrder() {
		return idOrder;
	}

	public OrderDetail() {
		
	}

	public int getIdOrderDetail() {
		return this.idOrderDetail;
	}

	public void setIdOrderDetail(int idOrderDetail) {
		this.idOrderDetail = idOrderDetail;
	}



	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}