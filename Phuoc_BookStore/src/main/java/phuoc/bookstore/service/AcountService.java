package phuoc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import phuoc.bookstore.entity.Acount;


public interface AcountService {

	void deleteAll();

	void deleteAll(Iterable<? extends Acount> entities);

	void delete(Acount entity);

	<S extends Acount> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(String id);

	long count();

	<S extends Acount> List<S> findAll(Example<S> example);

	<S extends Acount> boolean exists(Example<S> example);

	<S extends Acount> long count(Example<S> example);

	Acount getOne(String id);

	void deleteAllInBatch();

	<S extends Acount> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Acount> entities);

	boolean existsById(String id);

	<S extends Acount> S saveAndFlush(S entity);

	void flush();

	Optional<Acount> findById(String id);

	<S extends Acount> List<S> saveAll(Iterable<S> entities);

	List<Acount> findAllById(Iterable<String> ids);

	List<Acount> findAll(Sort sort);

	List<Acount> findAll();

	Page<Acount> findAll(Pageable pageable);

	<S extends Acount> Optional<S> findOne(Example<S> example);

	<S extends Acount> S save(S entity);

	Page<Acount> findAllByActivated(int activated, Pageable pageable);

	Page<Acount> findByUsernameContainingAndActivated(String username, int activated, Pageable pageable);

	List<Acount> findByActivatedContaining(String username);
	
	public Acount findByUsername(String username);

	boolean checkLogin(String username, String password);

	boolean checkLogin1(String username, String password);

	Optional<Acount> findByIdAndActivated(String id, int activated);


}
