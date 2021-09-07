package fptu.phuochg.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fptu.phuochg.library.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>  {
	

}
