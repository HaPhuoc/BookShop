package phuoc.bookstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import phuoc.bookstore.entity.Acount;

public interface AcountsDAO extends JpaRepository<Acount, String> {
	
	List<Acount> findByActivatedContaining(String username);
	Page<Acount> findByUsernameContainingAndActivated(String username,int activated,Pageable pageable);
	Page<Acount> findAllByActivated(int activated,Pageable pageable);
	
	List<Acount> findByUsernameAndActivated(String username,int activated);
	
	List<Acount> findByUsername(String username);
	
	Optional<Acount> findByActivatedAndUsername(int activated,String username);
	

}
