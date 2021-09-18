package phuoc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import phuoc.bookstore.entity.Order;

public interface OrdersService {

	void deleteAll();

	void deleteAll(Iterable<? extends Order> entities);

	void delete(Order entity);

	<S extends Order> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends Order> List<S> findAll(Example<S> example);

	<S extends Order> boolean exists(Example<S> example);

	<S extends Order> long count(Example<S> example);

	Order getOne(Integer id);

	void deleteAllInBatch();

	<S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Order> entities);

	boolean existsById(Integer id);

	<S extends Order> S saveAndFlush(S entity);

	void flush();

	Optional<Order> findById(Integer id);

	<S extends Order> List<S> saveAll(Iterable<S> entities);

	List<Order> findAllById(Iterable<Integer> ids);

	List<Order> findAll(Sort sort);

	List<Order> findAll();

	Page<Order> findAll(Pageable pageable);

	<S extends Order> Optional<S> findOne(Example<S> example);

	<S extends Order> S save(S entity);
	
	public Order findByIdOrder(int idOrder);

}
