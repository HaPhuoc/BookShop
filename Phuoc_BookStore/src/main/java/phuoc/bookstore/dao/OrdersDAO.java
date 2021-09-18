package phuoc.bookstore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import phuoc.bookstore.entity.Order;

public interface OrdersDAO extends JpaRepository<Order, Integer> {
	
	Page<Order> findByAddressContaining(String username,Pageable page);
	List<Order> findByIdOrder(int idOrder);
	@Query("select o FROM Order o WHERE o.acount.username=?1")
	List<Order> findByUsername(String username);
	@Query("select max(idOrder) from Order")
	String myQuery();
	@Query("select o FROM Order o WHERE o.acount.username=?1")
	Page<Order> findByUsername(String username,Pageable pageable);
	@Query("select o FROM Order o WHERE o.acount.username=?1 and o.status=?2")
	Page<Order> findByUsernameAndStatus(String username,int status,Pageable pageable);
	@Query("select count(idOrder) from Order")
	String soLuongHoaDon();
	@Query("select sum(total)*20/100 from Order")
	String doanhThu();
	@Query("select sum(total) from Order")
	String soVonNhapHang();
}
