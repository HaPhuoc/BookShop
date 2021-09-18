package phuoc.bookstore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import phuoc.bookstore.entity.Product;

public interface ProductsDAO extends JpaRepository<Product, Integer> {
	
	Page<Product> findByNameContainingAndAvailable(String name,int available,Pageable pageable);
	Page<Product> findByAvailable(int activated, Pageable pageable);
	
	List<Product> findByAvailable(int activated);
	
	List<Product> findByIdProduct(int idProduct);
	
	
	@Query("select sum(quantity) from Product")
	String tongSanPham();
	@Query("select sum(saled) from Product where available=1")
	String sanPhamDaBan();
	@Query("select max(saled) from Product ")
	String sanPhamBanChayNhat();
	
	@Query("select name from Product where saled=?1")
	String findBySaled(int saled);
	
	

}
