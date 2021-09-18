package phuoc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import phuoc.bookstore.entity.Product;

public interface ProductService {

	void deleteAll();

	void deleteAll(Iterable<? extends Product> entities);

	void delete(Product entity);

	<S extends Product> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends Product> List<S> findAll(Example<S> example);

	<S extends Product> boolean exists(Example<S> example);

	<S extends Product> long count(Example<S> example);

	Product getOne(Integer id);

	void deleteAllInBatch();

	<S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Product> entities);

	boolean existsById(Integer id);

	<S extends Product> S saveAndFlush(S entity);

	void flush();

	Optional<Product> findById(Integer id);

	<S extends Product> List<S> saveAll(Iterable<S> entities);

	List<Product> findAllById(Iterable<Integer> ids);

	List<Product> findAll(Sort sort);

	List<Product> findAll();

	Page<Product> findAll(Pageable pageable);

	<S extends Product> Optional<S> findOne(Example<S> example);

	<S extends Product> S save(S entity);

	List<Product> findByAvailable(int activated);

	Page<Product> findByAvailable(int activated, Pageable pageable);

	Page<Product> findByNameContainingAndAvailable(String name, int available, Pageable pageable);
	
	public Product findByIdProduct(int idProduct);

	List<Product> getAll();
	

}
