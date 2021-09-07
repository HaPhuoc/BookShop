package fptu.phuochg.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fptu.phuochg.library.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
