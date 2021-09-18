package phuoc.bookstore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import phuoc.bookstore.entity.Category;

public interface CategoriesDAO extends JpaRepository<Category, Integer>{

	List<Category> findByNameContaining(String name);
	Page<Category> findByNameContainingAndActivated(String name,int activated,Pageable pageable);
	Page<Category> findByActivated(int activated, Pageable pageable);
	
	List<Category> findByActivated(int activated);
	@Query("update Category set activated = 0" + " where idCategory = ?1")
	List<Category> updateByIdCategory(String idCategory);
	
	@Query("select count(idCategory) from Category where activated=1")
	String soLuongDanhMuc();
}
