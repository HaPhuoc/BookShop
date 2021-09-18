package phuoc.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import phuoc.bookstore.entity.OrderDetail;

public interface OrdersDetailsDAO extends JpaRepository<OrderDetail, Integer> {
	
	@Query("select od from OrderDetail od where od.idOrder.idOrder=?1")
	List<OrderDetail> findByIdOrder(int idOrder);

}
