package phuoc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import phuoc.bookstore.entity.Category;

public interface CategoryService {

	void deleteAll();

	void deleteAll(Iterable<? extends Category> entities);

	void delete(Category entity);

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends Category> List<S> findAll(Example<S> example);

	<S extends Category> boolean exists(Example<S> example);

	<S extends Category> long count(Example<S> example);

	Category getOne(Integer id);

	void deleteAllInBatch();

	<S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Category> entities);

	boolean existsById(Integer id);

	<S extends Category> S saveAndFlush(S entity);

	void flush();

	Optional<Category> findById(Integer id);

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	List<Category> findAllById(Iterable<Integer> ids);

	List<Category> findAll(Sort sort);

	List<Category> findAll();

	Page<Category> findAll(Pageable pageable);

	<S extends Category> Optional<S> findOne(Example<S> example);

	<S extends Category> S save(S entity);

	List<Category> updateByIdCategory(String idCategory);

	List<Category> findByActivated(int activated);

	Page<Category> findByActivated(int activated, Pageable pageable);

	Page<Category> findByNameContainingAndActivated(String name, int activated, Pageable pageable);

	List<Category> findByNameContaining(String name);

}
