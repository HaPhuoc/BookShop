package fptu.phuochg.library.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fptu.phuochg.library.domain.Book;
import fptu.phuochg.library.repository.BookRepository;
import fptu.phuochg.library.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;

	@Override
	public <S extends Book> S save(S entity) {
		return bookRepository.save(entity);
	}

	@Override
	public <S extends Book> Optional<S> findOne(Example<S> example) {
		return bookRepository.findOne(example);
	}

	@Override
	public Page<Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findAll(Sort sort) {
		return bookRepository.findAll(sort);
	}

	@Override
	public List<Book> findAllById(Iterable<Long> ids) {
		return bookRepository.findAllById(ids);
	}

	@Override
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

	@Override
	public <S extends Book> List<S> saveAll(Iterable<S> entities) {
		return bookRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		bookRepository.flush();
	}

	@Override
	public <S extends Book> S saveAndFlush(S entity) {
		return bookRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return bookRepository.existsById(id);
	}

	@Override
	public <S extends Book> List<S> saveAllAndFlush(Iterable<S> entities) {
		return bookRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
		return bookRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Book> entities) {
		bookRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Book> long count(Example<S> example) {
		return bookRepository.count(example);
	}

	@Override
	public <S extends Book> boolean exists(Example<S> example) {
		return bookRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Book> entities) {
		bookRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return bookRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		bookRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Book entity) {
		bookRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		bookRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		bookRepository.deleteAllInBatch();
	}

	@Override
	public Book getOne(Long id) {
		return bookRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Book> entities) {
		bookRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		bookRepository.deleteAll();
	}

	@Override
	public Book getById(Long id) {
		return bookRepository.getById(id);
	}

	@Override
	public <S extends Book> List<S> findAll(Example<S> example) {
		return bookRepository.findAll(example);
	}

	@Override
	public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
		return bookRepository.findAll(example, sort);
	}

	@Override
	public List<Book> findAllById(Long bookId) {
		return null;
	}

	@Override
	public <S extends Book> List<S> findAll(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findByIdBookId(Long idBook) {
		// TODO Auto-generated method stub
		for(Book book: bookRepository.findAll()) {
			if(book.getBookId()==idBook) {
				return book;
			}
		}
		return null;
	}
}

