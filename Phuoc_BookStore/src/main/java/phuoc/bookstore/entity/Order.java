package phuoc.bookstore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrder;

	private String address;

	private String createDate;
	
	private float total;

	private int status;
	
//	private int Status;
//	public int getStatus() {
//		return Status;
//	}
//
//	public void setStatus(int status) {
//		Status = status;
//	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	//bi-directional many-to-one association to Acount
	@ManyToOne
	@JoinColumn(name="Username")
	private Acount acount;
	
	@OneToMany(mappedBy = "idOrder",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;

	
	public Order() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Acount getAcount() {
		return this.acount;
	}

	public void setAcount(Acount acount) {
		this.acount = acount;
	}

}