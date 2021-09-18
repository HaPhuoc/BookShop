package phuoc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import phuoc.bookstore.dao.CategoriesDAO;
import phuoc.bookstore.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoriesDAO caDAO;

	@Override
	public List<Category> findByNameContaining(String name) {
		return caDAO.findByNameContaining(name);
	}

	@Override
	public Page<Category> findByNameContainingAndActivated(String name, int activated, Pageable pageable) {
		return caDAO.findByNameContainingAndActivated(name, activated, pageable);
	}

	@Override
	public Page<Category> findByActivated(int activated, Pageable pageable) {
		return caDAO.findByActivated(activated, pageable);
	}

	@Override
	public List<Category> findByActivated(int activated) {
		return caDAO.findByActivated(activated);
	}

	@Override
	public List<Category> updateByIdCategory(String idCategory) {
		return caDAO.updateByIdCategory(idCategory);
	}

	@Override
	public <S extends Category> S save(S entity) {
		return caDAO.save(entity);
	}

	@Override
	public <S extends Category> Optional<S> findOne(Example<S> example) {
		return caDAO.findOne(example);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return caDAO.findAll(pageable);
	}

	@Override
	public List<Category> findAll() {
		return caDAO.findAll();
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return caDAO.findAll(sort);
	}

	@Override
	public List<Category> findAllById(Iterable<Integer> ids) {
		return caDAO.findAllById(ids);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return caDAO.saveAll(entities);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return caDAO.findById(id);
	}

	@Override
	public void flush() {
		caDAO.flush();
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return caDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return caDAO.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Category> entities) {
		caDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return caDAO.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		caDAO.deleteAllInBatch();
	}

	@Override
	public Category getOne(Integer id) {
		return caDAO.getOne(id);
	}

	@Override
	public <S extends Category> long count(Example<S> example) {
		return caDAO.count(example);
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		return caDAO.exists(example);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example) {
		return caDAO.findAll(example);
	}

	@Override
	public long count() {
		return caDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		caDAO.deleteById(id);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		return caDAO.findAll(example, sort);
	}

	@Override
	public void delete(Category entity) {
		caDAO.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		caDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		caDAO.deleteAll();
	}
	
}
