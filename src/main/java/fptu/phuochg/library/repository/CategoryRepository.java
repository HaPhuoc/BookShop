package fptu.phuochg.library.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fptu.phuochg.library.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	/* List<Category> findByNameContaining(String name); */

	List<Category> findByNameContaining(String name);
	
	Page<Category> findByNameContaining(String name,Pageable pageable);
}
