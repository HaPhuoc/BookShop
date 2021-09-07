package fptu.phuochg.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fptu.phuochg.library.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

	List<Customer> findByNameContaining(String name);
  
	List<Customer> findByCustomerId(Long customerId);
	
	Optional<Customer> findByName(String name);
	
	

}
