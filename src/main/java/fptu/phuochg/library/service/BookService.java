package fptu.phuochg.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fptu.phuochg.library.domain.Book;

public interface BookService {

	<S extends Book> List<S> findAll(Example<S> example, Sort sort);

	<S extends Book> List<S> findAll(Long bookId);

	Book getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Book> entities);

	Book getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(Book entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	long count();

	void deleteAllInBatch(Iterable<Book> entities);

	<S extends Book> boolean exists(Example<S> example);

	<S extends Book> long count(Example<S> example);

	void deleteInBatch(Iterable<Book> entities);

	<S extends Book> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Book> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends Book> S saveAndFlush(S entity);

	void flush();

	<S extends Book> List<S> saveAll(Iterable<S> entities);

	Optional<Book> findById(Long id);

	List<Book> findAllById(Long bookId);

	List<Book> findAll(Sort sort);

	List<Book> findAll();

	Page<Book> findAll(Pageable pageable);

	<S extends Book> Optional<S> findOne(Example<S> example);

	<S extends Book> S save(S entity);

	<S extends Book> List<S> findAll(Example<S> example);

	List<Book> findAllById(Iterable<Long> ids);

	public Book findByIdBookId(Long idBook);


}
